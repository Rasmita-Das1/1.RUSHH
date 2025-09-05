package app.ecommerce.rushh.util;

import app.ecommerce.rushh.dto.ProductDTO;
import app.ecommerce.rushh.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDto(Product product) {
        if (product == null) return null;

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory().name())
                .idealFor(product.getIdealFor().name())
                .color(product.getColor().name())
                .size(product.getSize().name())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .brand(product.getBrand().name())
                .image(product.getImage())
                .seller(product.getSeller())
                .warranty(product.getWarranty())
                .description(product.getDescription())
                .rating(product.getRating())
                .reviews(product.getReviews())
                .quantity(product.getQuantity())
                .build();
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) return null;

        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .category(Enum.valueOf(app.ecommerce.rushh.enums.ProductCategory.class, dto.getCategory()))
                .idealFor(Enum.valueOf(app.ecommerce.rushh.enums.IdealFor.class, dto.getIdealFor()))
                .color(Enum.valueOf(app.ecommerce.rushh.enums.Color.class, dto.getColor()))
                .size(Enum.valueOf(app.ecommerce.rushh.enums.Size.class, dto.getSize()))
                .price(dto.getPrice())
                .discountPrice(dto.getDiscountPrice())
                .brand(Enum.valueOf(app.ecommerce.rushh.enums.Brand.class, dto.getBrand()))
                .image(dto.getImage())
                .seller(dto.getSeller())
                .warranty(dto.getWarranty())
                .description(dto.getDescription())
                .rating(dto.getRating())
                .reviews(dto.getReviews())
                .quantity(dto.getQuantity())
                .build();
    }
}
