package dev.charu.productcatalogservice.services;

import dev.charu.productcatalogservice.Client.FakeStoreClient;
import dev.charu.productcatalogservice.Client.FakeStoreProductDto;
import dev.charu.productcatalogservice.dtos.ProductDto;
import dev.charu.productcatalogservice.exception.NotFoundException;
import dev.charu.productcatalogservice.models.Category;
import dev.charu.productcatalogservice.models.Product;
import io.micrometer.common.lang.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Primary
public class FakeStoreProductServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    private Map<Long, Object> fakeStoreProducts = new HashMap<>();

    private RedisTemplate<Long, Object> redisTemplate;


    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient,RedisTemplate redisTemplate) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
        this.redisTemplate=redisTemplate;

    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();

        List<Product> answer = new ArrayList<>();

        for (FakeStoreProductDto productDto: fakeStoreProductDtos) {
            answer.add(convertFakeStoreProductDtoToProduct(productDto));
        }

        return answer;
    }


    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
//        FakeStoreProductDto fakeStoreProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get(productId, "PRODUCTS");
//
//        if (fakeStoreProductDto != null) {
//            return Optional.of(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
//        }
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto> response =  restTemplate.getForEntity(
//                "https://fakestoreapi.com/products/{id}",
//                FakeStoreProductDto.class, productId);
//
//        FakeStoreProductDto productDto = response.getBody();
////        fakeStoreProducts.put(productId, productDto);
//        if (productDto == null) {
//            return Optional.empty();
//        }
//       redisTemplate.opsForHash().put(productId, "PRODUCTS", productDto);
//
//        return Optional.of(convertFakeStoreProductDtoToProduct(productDto));

        FakeStoreProductDto fakeStoreProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get(productId, "PRODUCTS");

        if (fakeStoreProductDto != null) {
            return Optional.of(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        Optional<FakeStoreProductDto>productDto=fakeStoreClient.getSingleProduct(productId);

        if (productDto.isEmpty()) {
            return Optional.empty();
        }
        FakeStoreProductDto productDto1 = productDto.get();
//        fakeStoreProducts.put(productId, productDto);

        redisTemplate.opsForHash().put(productId, "PRODUCTS", productDto1);

        return Optional.of(convertFakeStoreProductDtoToProduct(productDto1));
    }

    @Override
    public Optional<Product> addNewProduct(ProductDto product) {


//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
//                "https://fakestoreapi.com/products",
//                product,
//                FakeStoreProductDto.class
//        );

        //FakeStoreProductDto productDto = response.getBody()

        Optional<FakeStoreProductDto>productDto=fakeStoreClient.addNewProduct(product);


        return Optional.of(convertFakeStoreProductDtoToProduct(productDto.get()));

        //return convertFakeStoreProductDtoToProduct(productDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        FakeStoreProductDto fakeStoreProductDtoResponse= fakeStoreClient.updateProduct(productId,product);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponse);


//        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate.pu
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }

}
