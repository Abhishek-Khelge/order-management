package com.order.management.db;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public class RepositoryInitializer {

    public void initializeProductDetails(Map<Integer, ProductDetails> productDetailsRepository) {
        productDetailsRepository.put(1, getLaptopProductDetails());
        productDetailsRepository.put(2, createTShirt());
        productDetailsRepository.put(3, createBag());
        productDetailsRepository.put(4, createBook());
        productDetailsRepository.put(5, createSmartphone());
        productDetailsRepository.put(6, createWatch());
        productDetailsRepository.put(7, createDeskChair());
    }

    private ProductDetails getLaptopProductDetails() {
        return ProductDetails.builder()
                .productType("Laptop")
                .productName("Example Laptop")
                .category("Electronics")
                .price(new BigDecimal("999.99"))
                .quantity(50)
                .manufacturer("TechCo")
                .details(Map.of(
                        "screenSize", "15 inches",
                        "ram", "8GB",
                        "storage", "512GB SSD"
                ))
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
    }

    public ProductDetails createTShirt() {
        return ProductDetails.builder()
                .productType("T-Shirt")
                .productName("Example T-Shirt")
                .category("Apparel")
                .price(new BigDecimal("19.99"))
                .quantity(200)
                .manufacturer("FashionHub")
                .details(Map.of(
                        "size", "Medium",
                        "color", "Blue",
                        "material", "Cotton"
                ))
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
    }

    public ProductDetails createBag() {
        return ProductDetails.builder()
                .productType("Bag")
                .productName("Example Bag")
                .category("Accessories")
                .price(new BigDecimal("49.99"))
                .quantity(100)
                .manufacturer("StyleCraft")
                .details(Map.of(
                        "type", "Backpack",
                        "color", "Black",
                        "material", "Leather"
                ))
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
    }

    public ProductDetails createBook() {
        return ProductDetails.builder()
                .productType("Book")
                .productName("Example Book")
                .category("Literature")
                .price(new BigDecimal("12.99"))
                .quantity(150)
                .author("John Doe")
                .details(Map.of(
                        "genre", "Mystery",
                        "pages", 300,
                        "language", "English"
                ))
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
    }

    public ProductDetails createSmartphone() {
        return ProductDetails.builder()
                .productType("Smartphone")
                .productName("Example Smartphone")
                .category("Electronics")
                .price(new BigDecimal("799.99"))
                .quantity(75)
                .manufacturer("TechGadget")
                .details(Map.of(
                        "brand", "TechBrand",
                        "screenSize", "6 inches",
                        "storage", "128GB"
                ))
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
    }

    public ProductDetails createWatch() {
        return ProductDetails.builder()
                .productType("Watch")
                .productName("Example Watch")
                .category("Accessories")
                .price(new BigDecimal("149.99"))
                .quantity(120)
                .manufacturer("TimeStyle")
                .details(Map.of(
                        "type", "Analog",
                        "color", "Silver",
                        "material", "Stainless Steel"
                ))
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
    }

    public ProductDetails createDeskChair() {
        return ProductDetails.builder()
                .productType("Desk Chair")
                .productName("Example Desk Chair")
                .category("Furniture")
                .price(new BigDecimal("199.99"))
                .quantity(50)
                .manufacturer("ComfortZone")
                .details(Map.of(
                        "material", "Leather",
                        "color", "Black",
                        "adjustable", true
                ))
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
    }
}