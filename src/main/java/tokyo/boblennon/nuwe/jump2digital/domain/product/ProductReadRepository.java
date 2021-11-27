package tokyo.boblennon.nuwe.jump2digital.domain.product;

import reactor.core.publisher.Mono;

public interface ProductReadRepository {
    
    public Mono<Product> findById(String id);

}
