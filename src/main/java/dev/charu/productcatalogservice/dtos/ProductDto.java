package dev.charu.productcatalogservice.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Service
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
    private RatingDto rating;
}
