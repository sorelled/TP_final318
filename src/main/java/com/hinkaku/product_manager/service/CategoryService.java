package com.hinkaku.product_manager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hinkaku.product_manager.model.Category;
import com.hinkaku.product_manager.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Ajouter une nouvelle catégorie
    public Category addCategory(Category category) {
        if (categoryRepository.existsByNomCategory(category.getNomCategory())) {
            throw new IllegalArgumentException("Cette catégorie existe déjà.");
        }
        return categoryRepository.save(category);
    }

    // Récupérer toutes les catégories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Récupérer une catégorie par son ID
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée !"));
    }

    // Mettre à jour une catégorie
    public Category updateCategory(UUID id, Category updatedCategory) {
        return categoryRepository.findById(id).map(existingCategory -> {
            if (!existingCategory.getNomCategory().equals(updatedCategory.getNomCategory()) &&
                    categoryRepository.existsByNomCategory(updatedCategory.getNomCategory())) {
                throw new IllegalArgumentException("Ce nom de catégorie est déjà utilisé");
            }
            existingCategory.setNomCategory(updatedCategory.getNomCategory());
            return categoryRepository.save(existingCategory);
        }).orElseThrow(() -> new RuntimeException("Catégorie non trouvée !"));
    }

    // Supprimer une catégorie
    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée !"));

        if (!category.getProducts().isEmpty()) {
            throw new IllegalStateException("Impossible de supprimer une catégorie contenant des produits");
        }

        categoryRepository.deleteById(id);
    }
}