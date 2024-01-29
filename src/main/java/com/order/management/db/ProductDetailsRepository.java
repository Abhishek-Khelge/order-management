package com.order.management.db;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class ProductDetailsRepository {

    private static Map<Integer, ProductDetails> productDetailsRepositories;
    RepositoryInitializer repositoryInitializer;

    public ProductDetailsRepository() {
        productDetailsRepositories = new HashMap<>();
        repositoryInitializer = new RepositoryInitializer();
        repositoryInitializer.initializeProductDetails(productDetailsRepositories);
    }

    public static Map<Integer, ProductDetails> getProductDetailsRepositories() {
        return productDetailsRepositories;
    }

    public ProductDetails getProductDetails(Integer productId) {
        if (productDetailsRepositories.containsKey(productId)) {
            return productDetailsRepositories.get(productId);
        } else {
            // todo throw exception
        }
        return null;
    }
}