package tokyo.boblennon.nuwe.jump2digital.domain.ticket;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class TicketProjection {
    
    @Field(name = "_id")
    private String paymentType;

    private Integer amount;

}
