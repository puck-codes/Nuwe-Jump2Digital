package tokyo.boblennon.nuwe.jump2digital.domain.product;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "products")
public @Getter @Setter @NoArgsConstructor class Product {

    @Id
    @NotNull
    private UUID id;

    @NotEmpty
    private String name;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @ProductTypeAnnotation(enumClass = ProductTypeEnum.class)
    private String desc;

}
