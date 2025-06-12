package app.ecommerce.rushh.service;

import app.ecommerce.rushh.dto.ProductDTO;

import java.util.List;

public interface ProductService {
	
    ProductDTO createProduct(ProductDTO dto);
    
    List<ProductDTO> getAllProducts();
    
    ProductDTO getProductById(Long id);
    
    ProductDTO updateProduct(Long id, ProductDTO dto);
    
    void deleteProduct(Long id);
    
}
