package tokyo.boblennon.nuwe.jump2digital.domain.analytics;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tokyo.boblennon.nuwe.jump2digital.domain.product.ProductProjection;
import tokyo.boblennon.nuwe.jump2digital.domain.ticket.TicketProjection;

public @Getter @Setter @NoArgsConstructor class Analytics {

    private Double totalBenefit;
    private List<ProductProjection> productsList = new ArrayList<>();
    private List<TicketProjection> ticketsList = new ArrayList<>();

}
