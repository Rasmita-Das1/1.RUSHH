package app.ecommerce.rushh.model;

import java.math.BigDecimal;

import app.ecommerce.rushh.enums.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Pattern(
    	    regexp = "^[0-9]+(\\.[0-9]+)?\\s?(Pc|Unit|kg|g|ml|L|Liter)$",
    	    message = "Invalid quantity format. Valid examples: 1 Pc, 500 ml, 1.5 kg"
    	)
    @Column(nullable = false)
    private String quantity;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    private IdealFor idealFor;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private Size size;

    private BigDecimal price;

    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    private String image;

    private String seller;

    private String warranty;

    @Column(length = 2000)
    private String description;

    private Double rating;

    private String reviews;
  
}
