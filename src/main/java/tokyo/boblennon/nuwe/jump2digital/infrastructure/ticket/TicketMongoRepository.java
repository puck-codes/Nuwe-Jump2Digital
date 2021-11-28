package tokyo.boblennon.nuwe.jump2digital.infrastructure.ticket;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import tokyo.boblennon.nuwe.jump2digital.domain.ticket.Ticket;

@Repository
public interface TicketMongoRepository extends ReactiveMongoRepository<Ticket, UUID> {

}
