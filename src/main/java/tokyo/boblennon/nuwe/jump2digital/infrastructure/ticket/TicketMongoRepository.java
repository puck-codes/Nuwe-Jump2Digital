package tokyo.boblennon.nuwe.jump2digital.infrastructure.ticket;

import java.util.UUID;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import tokyo.boblennon.nuwe.jump2digital.domain.ticket.Ticket;
import tokyo.boblennon.nuwe.jump2digital.domain.ticket.TicketProjection;

@Repository
public interface TicketMongoRepository extends ReactiveMongoRepository<Ticket, UUID> {

    @Aggregation(" { $group: { _id: $paymentType, amount: { $sum: 1 } } }, { $sort: { amount: 1 } }, { $project: { _id: 0, paymentType: $_id, amount: 1, } }")
    Flux<TicketProjection> findByPaymentType();

}
