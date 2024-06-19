package dev.charu.productcatalogservice.repository.JPA;

import dev.charu.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    Product findProductById(Long id);

    Product findProductByIdEquals(Long id);

    Product findByPriceBetweenAndTitleLessThanEqual(double greaterThanEqualPrice, double lessThanEqualPrice, String titleLessThan);



    Product findByPriceLessThanEqual(double price);




    List<Product> findByImageUrlIsNullOrderByIdDesc();

    List<Product> findByTitleIgnoreCaseStartingWith(String title);



    List<Product> findByTitleLikeIgnoreCase(String titleLike);

    Page<Product>findAll(Pageable pageable);


}
