package org.dorokhov.product_test.controller;

import org.dorokhov.product_test.exceptions.ResourceNotFoundException;
import org.dorokhov.product_test.model.Product;
import org.dorokhov.product_test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all")
    public List<Product> getAllEmployees() {
        return productRepository.findAll();
    }
    /*
    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Product product= productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found where id = " + id));
        return ResponseEntity.ok().body(product);
    }*/

    @GetMapping("/get/{title}")
    public ResponseEntity<Product> getProductByTitle(@PathVariable(value = "title") String title){
        Product product = productRepository.getProductByTitle(title);
        return ResponseEntity.ok().body(product);
    }


    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Product> updateProductById(@PathVariable(name = "id") Integer id,
                                              @RequestBody Product productDetails) throws ResourceNotFoundException{
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not found where id = " + id));
        product.setTitle(productDetails.getTitle());
        product.setDescription(productDetails.getDescription());
        final Product updateProduct = productRepository.save(product);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteProductById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Not found this product where id = " + id));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Complited", Boolean.TRUE);
        return response;
    }

}
