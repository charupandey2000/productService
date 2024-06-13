package dev.charu.productcatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableElasticsearchRepositories
public class ProductcatalogserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductcatalogserviceApplication.class, args);
    }

}
