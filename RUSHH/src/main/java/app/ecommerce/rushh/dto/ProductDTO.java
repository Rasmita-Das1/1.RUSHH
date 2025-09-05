package app.ecommerce.rushh.dto;

import app.ecommerce.rushh.enums.*;
import app.ecommerce.rushh.enums.Size;
import app.ecommerce.rushh.validation.ValueOfEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
	
	private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @ValueOfEnum(enumClass = ProductCategory.class, message = "Invalid category")
    private String category;

    @NotBlank
    @ValueOfEnum(enumClass = IdealFor.class, message = "Invalid idealFor value")
    private String idealFor;

    @NotBlank
    @ValueOfEnum(enumClass = Color.class, message = "Invalid color value")
    private String color;

    @NotBlank
    @ValueOfEnum(enumClass = Size.class, message = "Invalid size")
    private String size;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @DecimalMin("0.0")
    private BigDecimal discountPrice;

    @NotBlank
    @ValueOfEnum(enumClass = Brand.class, message = "Invalid brand")
    private String brand;

    private String image;

    private String seller;

    private String warranty;

    @jakarta.validation.constraints.Size(max = 2000)
    private String description;

    private Double rating;

    private String reviews;

    @NotBlank
    @Pattern(
        regexp = "^[0-9]+(\\.[0-9]+)?\\s?(Pc|Pcs|Unit|kg|g|ml|L|Liter)$",
        message = "Invalid quantity format. Valid examples: 1 Pc, 500 ml, 1.5 kg"
    )
    private String quantity;
}

