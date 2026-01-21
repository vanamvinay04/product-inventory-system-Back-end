package com.example.ProductInventorySystem.Service;
import com.example.ProductInventorySystem.model.Product;
import com.example.ProductInventorySystem.payload.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    ProductDto createProducts(ProductDto productDto);
    ProductDto getProductsById(Long id);
    List<ProductDto> getAllProducts();
    ProductDto updateProductById(Long id, ProductDto productDto);
    void deleteProductById(Long id);
}
