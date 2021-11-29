package tokyo.boblennon.nuwe.jump2digital.application.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tokyo.boblennon.nuwe.jump2digital.domain.analytics.Analytics;
import tokyo.boblennon.nuwe.jump2digital.domain.product.ProductProjection;
import tokyo.boblennon.nuwe.jump2digital.domain.ticket.Ticket;
import tokyo.boblennon.nuwe.jump2digital.domain.ticket.TicketProjection;
import tokyo.boblennon.nuwe.jump2digital.infrastructure.product.ProductRepositoryImp;
import tokyo.boblennon.nuwe.jump2digital.infrastructure.ticket.TicketRepositoryImp;

@Component
public class TicketHandler {

    private final Validator validator;
    private final TicketRepositoryImp ticketRepositoryImp;
    private final ProductRepositoryImp productRepositoryImp;

    @Autowired
    public TicketHandler(final ProductRepositoryImp productRepositoryImp, final TicketRepositoryImp ticketRepositoryImp,
            final Validator validator) {
        this.ticketRepositoryImp = ticketRepositoryImp;
        this.validator = validator;
        this.productRepositoryImp = productRepositoryImp;
    }

    Analytics analytics = new Analytics();
    Double total = 0.0;

    public Mono<ServerResponse> add(ServerRequest request) {
        Mono<Ticket> ticket = request.bodyToMono(Ticket.class);

        return ticket.flatMap(t -> {
            t.setId(UUID.randomUUID());
            Errors errs = new BeanPropertyBindingResult(t, Ticket.class.getName());
            this.validator.validate(t, errs);
            if (errs.hasErrors()) {
                return Flux.fromIterable(errs.getFieldErrors())
                        .map(err -> "Field " + err.getField() + " " + err.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().bodyValue(list));
            } else {
                return this.ticketRepositoryImp.add(t)
                        .flatMap(tData -> ServerResponse.created(URI
                                .create("/ticket"))
                                .contentType(APPLICATION_JSON)
                                .bodyValue(tData));
            }
        });
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));

        return this.ticketRepositoryImp.findById(id).flatMap(p -> ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .bodyValue(p))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        UUID id = UUID.fromString(request.pathVariable("id"));

        return this.ticketRepositoryImp.findById(id).flatMap(p -> this.ticketRepositoryImp.delete(p)
                .then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> analytics(ServerRequest request) {
        //! Resetea la variable total pero produce errores por tema de concurrencia
        total = 0.0;
        
        // Valor total de los productos vendidos
        this.ticketRepositoryImp.getAll().flatMap(t -> {
            return this.productRepositoryImp.findById(t.getProductId()).map(p -> {
                Double tmp = p.getPrice() * t.getAmount();
                return tmp;
            });
        }).subscribe(tmp -> analytics.setTotalBenefit(total += tmp));

        // Lista de productos agrupados por ProductType
        this.productRepositoryImp.findProductsByProductType()
                .collectList().subscribe(analytics::setProductsList);

        // Total de tickets PaymentType Visa y Mastercard
        this.ticketRepositoryImp.findByPaymentType()
                .collectList().subscribe(analytics::setTicketsList);

        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .bodyValue(analytics);
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(this.ticketRepositoryImp.getAll(), Ticket.class);
    }
}
