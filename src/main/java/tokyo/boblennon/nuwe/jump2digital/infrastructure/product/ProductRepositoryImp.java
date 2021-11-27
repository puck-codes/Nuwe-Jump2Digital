package tokyo.boblennon.nuwe.jump2digital.infrastructure.product;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import tokyo.boblennon.nuwe.jump2digital.domain.product.Product;
import tokyo.boblennon.nuwe.jump2digital.domain.product.ProductReadRepository;
import tokyo.boblennon.nuwe.jump2digital.domain.product.ProductWriteRepository;

@Service
public class ProductRepositoryImp implements ProductReadRepository, ProductWriteRepository {

    private final ProductMongoRepository productMongoRepository;

    @Autowired
    public ProductRepositoryImp(final ProductMongoRepository productMongoRepository) {
        this.productMongoRepository = productMongoRepository;
    }

    @Override
    public Mono<Product> add(Product product) {
        return this.productMongoRepository.save(product);
    }

    @Override
    public Mono<Product> update(Product product) {
        return this.productMongoRepository.save(product);
    }

    @Override
    public Mono<Void> delete(Product product) {
        return this.productMongoRepository.delete(product);
    }

    @Override
    public Mono<Product> findById(UUID id) {
        return this.productMongoRepository.findById(id);
    }

}
