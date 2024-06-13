package dev.charu.productcatalogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="CategoryMainTable")

public class Category extends BaseModel{
//private String name;
//private String discription;
//@OneToMany(mappedBy = "category")
//private List<Product> products;

    private String name;
    private String description;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = {CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 1)
    //private Set<Product> products;
    private List<Product> products;

}
