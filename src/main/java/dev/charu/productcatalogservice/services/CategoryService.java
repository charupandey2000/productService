package dev.charu.productcatalogservice.services;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    String getAllCategories();

    String getProductsInCategory(Long categoryId);
}
