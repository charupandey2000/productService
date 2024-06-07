package dev.charu.productcatalogservice.services;

import dev.charu.productcatalogservice.dtos.ProductDto;
import dev.charu.productcatalogservice.exception.NotFoundException;
import dev.charu.productcatalogservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getSingleProduct(Long productId) throws NotFoundException;

    Product addNewProduct(ProductDto product);


    Product updateProduct(Long productId, Product product);

    Product replaceProduct(Long productId, Product product);

    boolean deleteProduct(Long productId);


}
