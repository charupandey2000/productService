package dev.charu.productcatalogservice.dtos;

import lombok.*;
import org.springframework.stereotype.Service;

@Setter
@Getter
@ToString
@Service
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
    private RatingDto rating;
}
