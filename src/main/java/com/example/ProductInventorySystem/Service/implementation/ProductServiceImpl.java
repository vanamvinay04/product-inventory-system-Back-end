package com.example.ProductInventorySystem.Service.implementation;

import com.example.ProductInventorySystem.Service.ProductService;
import com.example.ProductInventorySystem.exception.ResourceNotFoundException;
import com.example.ProductInventorySystem.mapper.ProductMapper;
import com.example.ProductInventorySystem.model.Product;
import com.example.ProductInventorySystem.payload.ProductDto;
import com.example.ProductInventorySystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo){
        this.repo = repo;

    }


    @Override
    public ProductDto createProducts(ProductDto productDto) {
        Product product = ProductMapper.toProductEntity(productDto);
        Product savedProduct = repo.save(product);
        return ProductMapper.toProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductsById(Long id) {
        Product product = repo.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                String.format("Product not found whith ID %d",id))
                );
        return ProductMapper.toProductDto(product);
    }


    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = repo.findAll();
        return products.stream().map((product -> ProductMapper.toProductDto(product)))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProductById(Long id, ProductDto updatedProduct){
        Product product = repo.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format("Product nof found with the ID %d",id))
        );
        product.setProductName(updatedProduct.getProductName());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());
        product.setCategory(updatedProduct.getCategory());
        Product updatedProductObj = repo.save(product);
        return ProductMapper.toProductDto(updatedProductObj);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = repo.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format("the Product with ID %d is not there in our database.",id))
        );
        repo.deleteById(id);
    }
}
