package com.example.ProductInventorySystem.Controller;

import com.example.ProductInventorySystem.Service.ProductService;
import com.example.ProductInventorySystem.Service.implementation.ProductServiceImpl;
import com.example.ProductInventorySystem.model.Product;
import com.example.ProductInventorySystem.payload.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/products")
public class ProductController {

    private ProductService service; 

    public ProductController(ProductService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto savedProduct = service.createProducts(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductsById(@PathVariable  Long id){
        ProductDto getProductById = service.getProductsById(id);
        System.out.println(getProductById);
        if(getProductById != null)
            return new ResponseEntity<>(getProductById,HttpStatus.OK);
        else
            return new ResponseEntity<>("Products not found with that id NO:" + id,HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> getAllProducts = service.getAllProducts();
        return new ResponseEntity<>(getAllProducts , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable Long id,
                                                        @RequestBody ProductDto productDto){
       ProductDto updatedProduct =  service.updateProductById(id,productDto);
       return new ResponseEntity<>(updatedProduct,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){
       service.deleteProductById(id);
       return new ResponseEntity<>("Product with ID NO: "+ id +", deleted successfully.",HttpStatus.OK);
    }
}
