package dev.charu.productcatalogservice.repository;

import dev.charu.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    Product findProductById(Long id);

    Product findProductByIdEquals(Long id);

    Product findByPriceBetweenAndTitleLessThanEqual(double greaterThanEqualPrice, double lessThanEqualPrice, String titleLessThan);



    Product findByPriceLessThanEqual(double price);




    List<Product> findByImageUrlIsNullOrderByIdDesc();

    List<Product> findByTitleIgnoreCaseStartingWith(String title);



    List<Product> findByTitleLikeIgnoreCase(String titleLike);

}
