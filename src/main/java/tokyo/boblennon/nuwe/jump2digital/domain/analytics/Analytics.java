package tokyo.boblennon.nuwe.jump2digital.domain.analytics;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tokyo.boblennon.nuwe.jump2digital.domain.product.ProductProjection;
import tokyo.boblennon.nuwe.jump2digital.domain.ticket.TicketProjection;

public @Getter @Setter @NoArgsConstructor class Analytics {

    private Integer totalBenefit;
    private List<ProductProjection> soldProducts;
    private List<TicketProjection> ticketsList;

}
