package tokyo.boblennon.nuwe.jump2digital.infrastructure.product;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import tokyo.boblennon.nuwe.jump2digital.domain.product.Product;

@Repository
public interface ProductMongoRepository extends ReactiveMongoRepository<Product, String> {
    
}
