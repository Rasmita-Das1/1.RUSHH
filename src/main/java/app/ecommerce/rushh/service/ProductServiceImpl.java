package app.ecommerce.rushh.service;

import app.ecommerce.rushh.dto.ProductDTO;
import app.ecommerce.rushh.enums.Brand;
import app.ecommerce.rushh.enums.ProductCategory;
import app.ecommerce.rushh.exception.ResourceNotFoundException;
import app.ecommerce.rushh.model.Product;
import app.ecommerce.rushh.repository.ProductRepository;
import app.ecommerce.rushh.specification.ProductSpecification;
import app.ecommerce.rushh.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<ProductDTO> getProductsByCategory(String category) {
//        return productRepository.findByCategoryIgnoreCase(category)
//                .stream()
//                .map(productMapper::toDto)
//                .collect(Collectors.toList());
//    }
    
    @Override
    public List<ProductDTO> getProductsByCategory(String category) {
        ProductCategory enumCategory = ProductCategory.valueOf(category.toUpperCase());
        List<Product> products = productRepository.findByCategory(enumCategory);
        return products.stream().map(productMapper::toDto).collect(Collectors.toList());
    }

//    @Override
//    public List<ProductDTO> getProductsByBrand(String brand) {
//        return productRepository.findByBrandIgnoreCase(brand)
//                .stream()
//                .map(productMapper::toDto)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<ProductDTO> getProductsByBrand(String brand) {
        Brand enumBrand = Brand.valueOf(brand.toUpperCase());
        List<Product> products = productRepository.findByBrand(enumBrand);
        return products.stream().map(productMapper::toDto).collect(Collectors.toList());
    }
    
    @Override
    public List<ProductDTO> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(BigDecimal.valueOf(minPrice), BigDecimal.valueOf(maxPrice))
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsWithDiscount() {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getDiscountPrice() != null && p.getDiscountPrice().compareTo(p.getPrice()) < 0)
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getTopRatedProducts(double minRating) {
        return productRepository.findByRatingGreaterThanEqual(minRating)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchProducts(String keyword) {
        return productRepository.searchByKeyword(keyword.toLowerCase())
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<ProductDTO> filterProducts(String category, String brand, String idealFor, String color, String size, Double minPrice, Double maxPrice) {
//        Specification<Product> spec = ProductSpecification.filterBy(category, brand, idealFor, color, size, minPrice, maxPrice);
//        return productRepository.findAll(spec)
//                .stream()
//                .map(productMapper::toDto)
//                .collect(Collectors.toList());
//    }
    
    @Override
    public List<ProductDTO> filterProducts(String category, String brand, String idealFor, String color, String size, Double minPrice, Double maxPrice) {
        Specification<Product> spec = ProductSpecification.filterBy(category, brand, idealFor, color, size, minPrice, maxPrice);
        List<Product> products = productRepository.findAll(spec);
        return products.stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        Product updated = productMapper.toEntity(dto);
        updated.setId(existing.getId());

        return productMapper.toDto(productRepository.save(updated));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

	@Override
	public Page<ProductDTO> getAllProducts(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductDTO> searchProducts(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDTO toggleProductAvailability(Long id, boolean enabled) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> saveAllProducts(List<ProductDTO> products) {
		// TODO Auto-generated method stub
		return null;
	}
}