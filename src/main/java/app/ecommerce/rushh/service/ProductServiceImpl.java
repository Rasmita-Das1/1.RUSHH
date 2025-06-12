package app.ecommerce.rushh.service;

import app.ecommerce.rushh.dto.ProductDTO;
import app.ecommerce.rushh.model.Product;
import app.ecommerce.rushh.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = mapToEntity(dto);
        return mapToDTO(repository.save(product));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Product updated = mapToEntity(dto);
        updated.setId(existing.getId());

        return mapToDTO(repository.save(updated));
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    // DTO ↔ Entity mapping (can later use MapStruct or ModelMapper)
    private ProductDTO mapToDTO(Product product) {
    	
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setIdealFor(product.getIdealFor());
        dto.setColor(product.getColor());
        dto.setSize(product.getSize());
        dto.setPrice(product.getPrice());
        dto.setDiscountPrice(product.getDiscountPrice());
        dto.setBrand(product.getBrand());
        dto.setImage(product.getImage());
        dto.setSeller(product.getSeller());
        dto.setWarranty(product.getWarranty());
        dto.setDescription(product.getDescription());
        dto.setRating(product.getRating());
        return dto;
        
    }

    private Product mapToEntity(ProductDTO dto) {
    	
        return Product.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .idealFor(dto.getIdealFor())
                .color(dto.getColor())
                .size(dto.getSize())
                .price(dto.getPrice())
                .discountPrice(dto.getDiscountPrice())
                .brand(dto.getBrand())
                .image(dto.getImage())
                .seller(dto.getSeller())
                .warranty(dto.getWarranty())
                .description(dto.getDescription())
                .rating(dto.getRating())
                .build();
        
    }
}
