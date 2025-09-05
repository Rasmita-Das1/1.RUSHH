package app.ecommerce.rushh.specification;

import app.ecommerce.rushh.model.Product;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> filterBy(
            String category,
            String brand,
            String idealFor,
            String color,
            String size,
            Double minPrice,
            Double maxPrice
    ) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            Predicate predicate = cb.conjunction();

            if (category != null && !category.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("category")), category.toLowerCase()));
            }
            if (brand != null && !brand.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("brand")), brand.toLowerCase()));
            }
            if (idealFor != null && !idealFor.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("idealFor")), idealFor.toLowerCase()));
            }
            if (color != null && !color.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("color")), color.toLowerCase()));
            }
            if (size != null && !size.isEmpty()) {
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("size")), size.toLowerCase()));
            }
            if (minPrice != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("price"), BigDecimal.valueOf(minPrice)));
            }
            if (maxPrice != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("price"), BigDecimal.valueOf(maxPrice)));
            }

            return predicate;
        };
    }
}
