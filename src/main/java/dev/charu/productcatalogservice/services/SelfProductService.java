package dev.charu.productcatalogservice.services;

import dev.charu.productcatalogservice.dtos.ProductDto;
import dev.charu.productcatalogservice.exception.NotFoundException;
import dev.charu.productcatalogservice.models.Product;
import dev.charu.productcatalogservice.repository.JPA.ProductRepository;
import dev.charu.productcatalogservice.repository.ElasticSearch.searchRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service(value = "selfProductService")
@Primary

public class SelfProductService implements ProductService{
    private final ProductRepository productRepository;
    private searchRepository elasticSearchRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        Product product = productRepository.findProductById(productId);
        //Optional<Product>response=elasticSearchRepository.findById(productId);
//        if(response.isEmpty()){
//            throw new NotFoundException("Product Doesn't Exist");
//        }
//        Product product=response.get();
        if (product == null) {
            throw new NotFoundException("Product Doesn't Exist");
        }

        return Optional.of(product);
    }

    @Override
    public Optional<Product> addNewProduct(ProductDto product) {
        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setImageUrl(product.getImage());
        newProduct.setTitle(product.getTitle());
        newProduct.setPrice(product.getPrice());
        Product Responseproduct=productRepository.save(newProduct);
        Optional<Product>response= Optional.of(elasticSearchRepository.save(newProduct));
        return Optional.of(Responseproduct);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product ResponseProduct=productRepository.save(product);
        return ResponseProduct;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        Product product=productRepository.findProductById(productId);
        product.setDeleted(true);
        Product ResponseProduct=productRepository.save(product);
        return true;
    }

    @Override
    public Page<Product> getAll(int numberOfResult, int offset) {
        Page<Product>response=productRepository.findAll(PageRequest.of(offset/numberOfResult,numberOfResult));
        return response;
    }


}
