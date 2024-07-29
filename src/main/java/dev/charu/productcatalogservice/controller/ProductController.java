package dev.charu.productcatalogservice.controller;

import dev.charu.productcatalogservice.AuthenticationClient.AuthenticationClient;
import dev.charu.productcatalogservice.AuthenticationClient.Dtos.SessionStatus;
import dev.charu.productcatalogservice.AuthenticationClient.Dtos.ValidatetokenResponseDto;
import dev.charu.productcatalogservice.dtos.ProductDto;
import dev.charu.productcatalogservice.dtos.getProductDto;
import dev.charu.productcatalogservice.exception.NotFoundException;
import dev.charu.productcatalogservice.models.Category;
import dev.charu.productcatalogservice.models.Product;
import dev.charu.productcatalogservice.services.ProductService;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")

public class ProductController {
    public ProductService productservice;
    public AuthenticationClient authenticationClient;
    public ProductController(ProductService productservice,AuthenticationClient authenticationClient){
        this.productservice=productservice;
        this.authenticationClient=authenticationClient;
    }


    @GetMapping("/hello")
    public String sayhello(){
        String ans="hello hello!!!";
        return ans;
    }

//@GetMapping ("/{productid}")
//public Product getsingleproduct(@PathVariable("productid") long productid){
//        Product response= ProductService.GetSingleproduct(productid);
//        return response;
//}

/*public productresponsedtos getsingleproduct(@PathVariable("productid") long productid) throws Exception{

    productresponsedtos productresponsedtos=new productresponsedtos();
    if(ProductService.GetSingleproduct(productid)==null){
        Exception exception=new NotFoundException("Product is null");
        throw exception;
    }
    productresponsedtos.setProductdtos(ProductService.GetSingleproduct(productid));
    return productresponsedtos;
}*/

    @GetMapping("/page")
    public ResponseEntity<Page<Product>> getproduct( @RequestBody getProductDto getProductDto){
        return  ResponseEntity.of(Optional.ofNullable(productservice.getAll(getProductDto.getNumberOfResult(), getProductDto.getOffset())));
    }

@GetMapping("/{productId}")
public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

    headers.add(
            "auth-token", "noaccess4uheyhey"
    );


    Optional<Product> productOptional = productservice.getSingleProduct(productId);


    if (productOptional.isEmpty()) {
        throw new NotFoundException("No Product with product id: " + productId);
    }

    ResponseEntity<Product> response = new ResponseEntity(
            productservice.getSingleProduct(productId),
            headers,
            HttpStatus.NOT_FOUND
    );

    return response;

}


//@GetMapping()
//public List<Product> getallproduct(){
//    List<Product> response=productservice.GetAllproduct();
//    return response;
//}
@GetMapping()
public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token , @Nullable @RequestHeader("USER_ID") Long userId){
  if(token==null || userId==null){
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
}
    ValidatetokenResponseDto response=authenticationClient.validate(token,userId);
      //System.out.println("charu");
//    if (response.getSessionStatus().equals(SessionStatus.INVALID)) {
//        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//    boolean isadmin=false;
//    for(Role role:response.getUserDto().getRoles()){
//        if(role.getRoleName().equals("ADMIN")){
//            isadmin=true;
//        }
//    }
//    if(!isadmin){
//        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//    }


    List<Product> products = productservice.getAllProducts();


    return new ResponseEntity<>(products, HttpStatus.OK);
}

//    @GetMapping()
//    public ResponseEntity<List<Product>> getAllProducts(){
//        List<Product> products = productservice.getAllProducts();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

//@PostMapping()
//public Product addanewproduct(@RequestBody ProductDto product){
//    Product product1=new Product();
//    product1.setId(product.getId());
//    product1.setTitle(product.getTitle());
//    product1.setPrice(product.getPrice());
//    product1.setDescription(product.getDescription());
//    product1.setImageUrl(product.getImage());
//    Product response=productservice.Addnewproduct(product1);
//    return response;
//
//}

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product) {

        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setImageUrl(product.getImage());
        newProduct.setTitle(product.getTitle());
        newProduct.setPrice(product.getPrice());

        //newProduct = productRepository.save(newProduct);
        Optional<Product> Responseproduct =productservice.addNewProduct(product);

        Product product2=Responseproduct.get();

        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);

        return response;
    }


    /*public ResponseEntity<Product> addanewproduct(@RequestBody ProductDto Product){
    //productresponsedtos productresponsedtos=new productresponsedtos();
    //productresponsedtos.setProductdtos(ProductService.Addnewproduct(Product));
    ResponseEntity<Product>response=new ResponseEntity<>(ProductService.Addnewproduct(Product), HttpStatus.OK);
    return response;

}*/
/*@PatchMapping("/{productid}")
public productservicedtos update_a_product(@PathVariable ("productid") long productid, @RequestBody productupdatedto ProductDto ){
    productservicedtos response=ProductService.updateaproduct(productid,ProductDto);
    return response;

}*/
//@PutMapping("/{productid}")
//public Product update_a_product(@PathVariable ("productid") long productid, @RequestBody ProductDto product ) {
//    Product product1=new Product();
//    product1.setId(product.getId());
//    product1.setTitle(product.getTitle());
//    product1.setPrice(product.getPrice());
//    product1.setDescription(product.getDescription());
//    product1.setImageUrl(product.getImage());
//    Product response = productservice.updateaproduct(productid,product1);
//    return response;
//}


    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId,
                                 @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        return productservice.updateProduct(productId, product);
    }

@DeleteMapping("/{productId}")
public String deleteProduct(@PathVariable("productId") Long productId) {
        return "Deleting a product with id: " + productId;
    }

}
