package com.example.ProductInventorySystem.mapper;

import com.example.ProductInventorySystem.model.Product;
import com.example.ProductInventorySystem.payload.ProductDto;
import org.apache.logging.log4j.util.ProcessIdUtil;

public class ProductMapper {

    public static ProductDto toProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory()
        );
    }

    public static Product toProductEntity(ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getProductName(),
                productDto.getPrice(),
                productDto.getQuantity(),
                productDto.getCategory()
        );
    }

}
