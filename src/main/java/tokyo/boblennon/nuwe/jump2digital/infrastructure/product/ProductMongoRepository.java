package tokyo.boblennon.nuwe.jump2digital.infrastructure.product;

import java.util.UUID;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import tokyo.boblennon.nuwe.jump2digital.domain.product.Product;
import tokyo.boblennon.nuwe.jump2digital.domain.product.ProductProjection;

@Repository
public interface ProductMongoRepository extends ReactiveMongoRepository<Product, UUID> {

    @Aggregation(" { $group: { _id: $desc, amount: { $sum: 1 } } }, { $sort: { amount: 1 } }, { $project: { _id: 0, productType: $_id, amount: 1, } }")
    Flux<ProductProjection> findProductsByProductType();
    
}
