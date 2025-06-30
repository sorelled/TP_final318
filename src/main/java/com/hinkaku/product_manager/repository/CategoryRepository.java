package com.hinkaku.product_manager.repository;

import com.hinkaku.product_manager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>  {
    // Trouver une catégorie par nom (exact)
    Optional<Category> findByNomCategory(String nomCategory);
    // Vérifier si une catégorie existe par nom
    boolean existsByNomCategory(String nomCategory);
    // Lister toutes les catégories dont le nom contient une chaîne
    List<Category> findByNomCategoryContainingIgnoreCase(String keyword);
}
