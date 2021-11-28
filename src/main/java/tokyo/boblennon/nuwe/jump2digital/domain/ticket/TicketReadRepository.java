package tokyo.boblennon.nuwe.jump2digital.domain.ticket;

import java.util.UUID;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketReadRepository {
    
    public Flux<Ticket> getAll();
    public Mono<Ticket> findById(UUID id);
    
}
