package tokyo.boblennon.nuwe.jump2digital.application.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.net.URI;
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
import tokyo.boblennon.nuwe.jump2digital.domain.ticket.Ticket;
import tokyo.boblennon.nuwe.jump2digital.infrastructure.ticket.TicketRepositoryImp;

@Component
public class TicketHandler {

    private final Validator validator;
    private final TicketRepositoryImp ticketRepositoryImp;

    @Autowired
    public TicketHandler(final TicketRepositoryImp ticketRepositoryImp, final Validator validator) {
        this.ticketRepositoryImp = ticketRepositoryImp;
        this.validator = validator;
    }

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
}
