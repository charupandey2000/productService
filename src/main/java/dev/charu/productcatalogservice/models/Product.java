package dev.charu.productcatalogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="MainProductTable")
@Data
@Document(indexName = "product")
public class Product extends BaseModel {

    private String title;


    private double price;

    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Category category;

    private String imageUrl;

    private boolean isPublic;

}
