package com.hinkaku.product_manager.repository;

import com.hinkaku.product_manager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>  {
    Optional<Category> findByNomCategory(String nomCategory);
    boolean existsByNomCategory(String nomCategory);
    List<Category> findByNomCategoryContainingIgnoreCase(String keyword);
}
