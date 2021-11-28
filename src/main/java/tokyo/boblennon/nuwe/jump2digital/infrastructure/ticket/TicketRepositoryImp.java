package tokyo.boblennon.nuwe.jump2digital.infrastructure.ticket;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tokyo.boblennon.nuwe.jump2digital.domain.ticket.Ticket;
import tokyo.boblennon.nuwe.jump2digital.domain.ticket.TicketProjection;
import tokyo.boblennon.nuwe.jump2digital.domain.ticket.TicketReadRepository;
import tokyo.boblennon.nuwe.jump2digital.domain.ticket.TicketWriteRepository;

@Service
public class TicketRepositoryImp implements TicketReadRepository, TicketWriteRepository {

    private final TicketMongoRepository ticketMongoRepository;

    @Autowired
    public TicketRepositoryImp(final TicketMongoRepository ticketMongoRepository) {
        this.ticketMongoRepository = ticketMongoRepository;
    }

    @Override
    public Mono<Ticket> add(Ticket ticket) {
        return this.ticketMongoRepository.save(ticket);
    }

    @Override
    public Mono<Void> delete(Ticket ticket) {
        return this.ticketMongoRepository.delete(ticket);
    }

    @Override
    public Flux<Ticket> getAll() {
        return this.ticketMongoRepository.findAll();
    }

    @Override
    public Mono<Ticket> findById(UUID id) {
        return this.ticketMongoRepository.findById(id);
    }

    @Override
    public Flux<TicketProjection> findByPaymentType() {
        return this.ticketMongoRepository.findByPaymentType();
    }

}
