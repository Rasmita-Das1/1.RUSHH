package app.ecommerce.rushh.model;

//import app.ecommerce.rushh.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import app.ecommerce.rushh.enums.Brand;
import app.ecommerce.rushh.enums.Color;
import app.ecommerce.rushh.enums.IdealFor;
import app.ecommerce.rushh.enums.ProductCategory;
import app.ecommerce.rushh.enums.Size;

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
