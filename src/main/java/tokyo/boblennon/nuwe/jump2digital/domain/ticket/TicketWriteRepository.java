package tokyo.boblennon.nuwe.jump2digital.domain.ticket;

import reactor.core.publisher.Mono;

public interface TicketWriteRepository {

    public Mono<Ticket> add(Ticket ticket);
    public Mono<Void> delete(Ticket ticket);

}