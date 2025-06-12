package app.ecommerce.rushh.dto;

import app.ecommerce.rushh.enums.*;
import app.ecommerce.rushh.enums.Size;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Category is required")
    private ProductCategory category;

    @NotNull(message = "Target gender (idealFor) is required")
    private IdealFor idealFor;

    @NotNull(message = "Color is required")
    private Color color;

    @NotNull(message = "Size is required")
    private Size size;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    @DecimalMin(value = "0.0", inclusive = true, message = "Discount price cannot be negative")
    private BigDecimal discountPrice;

    @NotNull(message = "Brand is required")
    private Brand brand;

    @NotBlank(message = "Image URL or path is required")
    private String image;

    @NotBlank(message = "Seller is required")
    private String seller;

    private String warranty;

    @jakarta.validation.constraints.Size(max = 2000, message = "Description can't exceed 2000 characters")
    private String description;

    @DecimalMin(value = "0.0", message = "Rating must be between 0.0 and 5.0")
    @DecimalMax(value = "5.0", message = "Rating must be between 0.0 and 5.0")
    private Double rating;
    
}
