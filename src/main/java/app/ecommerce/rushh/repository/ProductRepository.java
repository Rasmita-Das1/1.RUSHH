package app.ecommerce.rushh.repository;

import app.ecommerce.rushh.enums.Brand;
import app.ecommerce.rushh.enums.Color;
import app.ecommerce.rushh.enums.IdealFor;
import app.ecommerce.rushh.enums.ProductCategory;
import app.ecommerce.rushh.enums.Size;
import app.ecommerce.rushh.model.Product;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
    List<Product> findByCategory(ProductCategory category);
	
    List<Product> findByBrand(Brand brand);

    List<Product> findByIdealFor(IdealFor idealFor);

    List<Product> findByColor(Color color);

    List<Product> findBySize(Size size);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByRatingGreaterThanEqual(Double rating);
    
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:keyword% OR LOWER(p.description) LIKE %:keyword% ORDER BY p.rating ASC")
    List<Product> searchByKeywordOrderByRatingAsc(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:keyword% OR LOWER(p.description) LIKE %:keyword% ORDER BY p.rating DESC")
    List<Product> searchByKeywordOrderByRatingDesc(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:keyword% OR LOWER(p.description) LIKE %:keyword% ORDER BY p.price ASC")
    List<Product> searchByKeywordOrderByPriceAsc(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:keyword% OR LOWER(p.description) LIKE %:keyword% ORDER BY p.price DESC")
    List<Product> searchByKeywordOrderByPriceDesc(@Param("keyword") String keyword);

    // Pagination support (basic keyword search with pageable)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:keyword% OR LOWER(p.description) LIKE %:keyword%")
    Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:keyword% OR LOWER(p.description) LIKE %:keyword%")
    List<Product> searchByKeyword(@Param("keyword") String keyword);
	
}
