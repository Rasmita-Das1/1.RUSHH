package app.ecommerce.rushh.controller;

import app.ecommerce.rushh.dto.ProductDTO;
import app.ecommerce.rushh.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    
    @PostMapping
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO dto) {
        return productService.createProduct(dto);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO dto) {
        return productService.updateProduct(id, dto);
    }

//    @PostMapping
//    public ProductDTO createProduct(@RequestBody ProductDTO dto) {
//        return productService.createProduct(dto);
//    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

//    @PutMapping("/{id}")
//    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
//        return productService.updateProduct(id, dto);
//    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
    
}
