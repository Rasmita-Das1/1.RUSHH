package app.ecommerce.rushh.service;

import app.ecommerce.rushh.dto.ProductDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
	
    ProductDTO createProduct(ProductDTO dto);
    
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);
    
    List<ProductDTO> getProductsByCategory(String category);

    List<ProductDTO> getProductsByBrand(String brand);

    List<ProductDTO> getProductsByPriceRange(double minPrice, double maxPrice);

    List<ProductDTO> getProductsWithDiscount(); // discountPrice < price

    List<ProductDTO> getTopRatedProducts(double minRating);
    
//    List<ProductDTO> searchProductsByRatingAsc(String keyword);
//
//    List<ProductDTO> searchProductsByRatingDesc(String keyword);
    
    List<ProductDTO> searchProductsByRatingAsc(String keyword);
    List<ProductDTO> searchProductsByRatingDesc(String keyword);
    List<ProductDTO> searchProductsByPriceAsc(String keyword);
    List<ProductDTO> searchProductsByPriceDesc(String keyword);
    
    List<ProductDTO> searchProducts(String keyword);

    Page<ProductDTO> searchProducts(String keyword, Pageable pageable);

//    List<ProductDTO> getProductsByFilters(String idealFor, String color, String size); 
    
    List<ProductDTO> filterProducts(String category, String brand, String idealFor, String color, String size, Double minPrice, Double maxPrice);  // multi-filter
    
    ProductDTO updateProduct(Long id, ProductDTO dto);
    
    Page<ProductDTO> getAllProducts(Pageable pageable);
    
    ProductDTO toggleProductAvailability(Long id, boolean enabled);

    List<ProductDTO> saveAllProducts(List<ProductDTO> products);

    
    void deleteProduct(Long id);
    
}
