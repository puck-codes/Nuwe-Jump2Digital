package tokyo.boblennon.nuwe.jump2digital.domain.ticket;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "tickets")
public @Getter @Setter @NoArgsConstructor class Ticket {

    @Id
    @NotNull
    private UUID id;
    
    @NotNull
    private UUID productId;
    
    @NotNull
    @Positive
    private Integer amount;

    @NotNull
    private PaymentTypeEnum paymentType;
    
}
