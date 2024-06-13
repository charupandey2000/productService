package dev.charu.productcatalogservice.repository;

import dev.charu.productcatalogservice.models.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Component
public interface SearchRepository extends ElasticsearchRepository<Product, Long> {
//    @Override
//    <S extends Product> S save(S entity);
//
//    @Override
//    Optional<Product> findById(Long aLong);

}
