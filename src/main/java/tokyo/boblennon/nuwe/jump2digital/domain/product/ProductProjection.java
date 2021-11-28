package tokyo.boblennon.nuwe.jump2digital.domain.product;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class ProductProjection {

    @Field(name = "_id")
    private String productType;

    private Integer amount;

}
