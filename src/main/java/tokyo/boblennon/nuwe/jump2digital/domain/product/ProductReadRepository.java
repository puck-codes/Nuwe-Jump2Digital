package tokyo.boblennon.nuwe.jump2digital.domain.product;

import java.util.UUID;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductReadRepository {

    public Mono<Product> findById(UUID id);
    public Flux<ProductProjection> findProductsByProductType();

}
