//package app.ecommerce.rushh.controller;
//
//import app.ecommerce.rushh.dto.ProductDTO;
//import app.ecommerce.rushh.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    // ✅ 1. Create Product
//    @PostMapping
//    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
//        return new ResponseEntity<>(productService.createProduct(dto), HttpStatus.CREATED);
//    }
//
//    // ✅ 2. Get Product by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
//        return ResponseEntity.ok(productService.getProductById(id));
//    }
//
//    // ✅ 3. Get All Products
//    @GetMapping
//    public ResponseEntity<List<ProductDTO>> getAllProducts() {
//        return ResponseEntity.ok(productService.getAllProducts());
//    }
//
//    // ✅ 4. Update Product
//    @PutMapping("/{id}")
//    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
//        return ResponseEntity.ok(productService.updateProduct(id, dto));
//    }
//
//    // ✅ 5. Delete Product
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    // ✅ 6. Get by Category
//    @GetMapping("/category/{category}")
//    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable String category) {
//        return ResponseEntity.ok(productService.getProductsByCategory(category));
//    }
//
//    // ✅ 7. Get by Brand
//    @GetMapping("/brand/{brand}")
//    public ResponseEntity<List<ProductDTO>> getByBrand(@PathVariable String brand) {
//        return ResponseEntity.ok(productService.getProductsByBrand(brand));
//    }
//
//    // ✅ 8. Price Range
//    @GetMapping("/price")
//    public ResponseEntity<List<ProductDTO>> getByPriceRange(@RequestParam double min, @RequestParam double max) {
//        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
//    }
//
//    // ✅ 9. Discounted Products
//    @GetMapping("/discounts")
//    public ResponseEntity<List<ProductDTO>> getDiscountedProducts() {
//        return ResponseEntity.ok(productService.getProductsWithDiscount());
//    }
//
//    // ✅ 10. Top Rated
//    @GetMapping("/top-rated")
//    public ResponseEntity<List<ProductDTO>> getTopRated(@RequestParam double rating) {
//        return ResponseEntity.ok(productService.getTopRatedProducts(rating));
//    }
//
//    // ✅ 11. Search by keyword
//    @GetMapping("/search")
//    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String keyword) {
//        return ResponseEntity.ok(productService.searchProducts(keyword));
//    }
//
//    // ✅ 12. Filter Products (Multi-filter)
//    @GetMapping("/filter")
//    public ResponseEntity<List<ProductDTO>> filterProducts(
//            @RequestParam(required = false) String category,
//            @RequestParam(required = false) String brand,
//            @RequestParam(required = false) String idealFor,
//            @RequestParam(required = false) String color,
//            @RequestParam(required = false) String size,
//            @RequestParam(required = false) Double minPrice,
//            @RequestParam(required = false) Double maxPrice
//    ) {
//        return ResponseEntity.ok(
//                productService.filterProducts(category, brand, idealFor, color, size, minPrice, maxPrice)
//        );
//    }
//
//    // ✅ 13. Sorted Searches
//    @GetMapping("/search/rating-asc")
//    public ResponseEntity<List<ProductDTO>> searchByRatingAsc(@RequestParam String keyword) {
//        return ResponseEntity.ok(productService.searchProductsByRatingAsc(keyword));
//    }
//
//    @GetMapping("/search/rating-desc")
//    public ResponseEntity<List<ProductDTO>> searchByRatingDesc(@RequestParam String keyword) {
//        return ResponseEntity.ok(productService.searchProductsByRatingDesc(keyword));
//    }
//
//    @GetMapping("/search/price-asc")
//    public ResponseEntity<List<ProductDTO>> searchByPriceAsc(@RequestParam String keyword) {
//        return ResponseEntity.ok(productService.searchProductsByPriceAsc(keyword));
//    }
//
//    @GetMapping("/search/price-desc")
//    public ResponseEntity<List<ProductDTO>> searchByPriceDesc(@RequestParam String keyword) {
//        return ResponseEntity.ok(productService.searchProductsByPriceDesc(keyword));
//    }
//    
//}

package app.ecommerce.rushh.controller;

import app.ecommerce.rushh.dto.ProductDTO;
import app.ecommerce.rushh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ✅ 1. Create Product — only ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.createProduct(dto), HttpStatus.CREATED);
    }

    // ✅ 2. Get Product by ID — all roles
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // ✅ 3. Get All Products — all roles
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // ✅ 4. Update Product — only ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    // ✅ 5. Delete Product — only ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ 6. Get by Category — all roles
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    // ✅ 7. Get by Brand — all roles
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<ProductDTO>> getByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(productService.getProductsByBrand(brand));
    }

    // ✅ 8. Price Range — all roles
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/price")
    public ResponseEntity<List<ProductDTO>> getByPriceRange(@RequestParam double min, @RequestParam double max) {
        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }

    // ✅ 9. Discounted Products — all roles
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/discounts")
    public ResponseEntity<List<ProductDTO>> getDiscountedProducts() {
        return ResponseEntity.ok(productService.getProductsWithDiscount());
    }

    // ✅ 10. Top Rated — all roles
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/top-rated")
    public ResponseEntity<List<ProductDTO>> getTopRated(@RequestParam double rating) {
        return ResponseEntity.ok(productService.getTopRatedProducts(rating));
    }

    // ✅ 11. Search by keyword — all roles
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProducts(keyword));
    }

    // ✅ 12. Filter Products (Multi-filter) — all roles
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/filter")
    public ResponseEntity<List<ProductDTO>> filterProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String idealFor,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        return ResponseEntity.ok(
                productService.filterProducts(category, brand, idealFor, color, size, minPrice, maxPrice)
        );
    }

    // ✅ 13. Sorted Searches — all roles
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/search/rating-asc")
    public ResponseEntity<List<ProductDTO>> searchByRatingAsc(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProductsByRatingAsc(keyword));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/search/rating-desc")
    public ResponseEntity<List<ProductDTO>> searchByRatingDesc(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProductsByRatingDesc(keyword));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/search/price-asc")
    public ResponseEntity<List<ProductDTO>> searchByPriceAsc(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProductsByPriceAsc(keyword));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/search/price-desc")
    public ResponseEntity<List<ProductDTO>> searchByPriceDesc(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProductsByPriceDesc(keyword));
    }
    
}

