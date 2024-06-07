package dev.charu.productcatalogservice.Client;

import dev.charu.productcatalogservice.dtos.ProductDto;
import dev.charu.productcatalogservice.exception.NotFoundException;
import dev.charu.productcatalogservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Component
@Setter
@Getter
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        return Arrays.asList(l.getBody());
    }

    public Optional<FakeStoreProductDto> getSingleProduct(Long productId) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class, productId);

        FakeStoreProductDto productDto = response.getBody();
        return Optional.of(productDto);
    }

    public Optional<FakeStoreProductDto> addNewProduct(ProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                FakeStoreProductDto.class
        );

        FakeStoreProductDto productDto=response.getBody();
        return Optional.of(productDto);
    }


     public FakeStoreProductDto updateProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
//        restTemplate.pat

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());


//        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
//                HttpMethod.PATCH,
//                "https://fakestoreapi.com/products/{id}",
//                fakeStoreProductDto,
//                FakeStoreProductDto.class,
//                productId
//        );

//        if (fakeStoreProductDtoResponseEntity.getHeaders())


        FakeStoreProductDto fakeStoreProductDtoResponse = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );
        return fakeStoreProductDtoResponse;
    }

    FakeStoreProductDto replaceProduct(Long productId, Product product) {
        return null;
    }

    FakeStoreProductDto deleteProduct(Long productId) {
        return null;
    }
}
