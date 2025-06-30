package com.hinkaku.product_manager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hinkaku.product_manager.dto.CategoryDto;
import com.hinkaku.product_manager.model.Category;
import com.hinkaku.product_manager.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Ajouter une nouvelle catégorie
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = convertToEntity(categoryDto);
        if (categoryRepository.existsByNomCategory(category.getNomCategory())) {
            throw new IllegalArgumentException("Cette catégorie existe déjà.");
        }
        return convertToDto(categoryRepository.save(category));
    }

    private Category convertToEntity(CategoryDto dto) {
        Category category = new Category();
        category.setIdCategory(dto.getIdCategory());
        category.setNomCategory(dto.getNomCategory());
        return category;
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setIdCategory(category.getIdCategory());
        dto.setNomCategory(category.getNomCategory());
        return dto;
    }

    // Récupérer toutes les catégories
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Récupérer une catégorie par son ID
    public CategoryDto getCategoryById(UUID id) {
        return convertToDto(categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée !")));
    }

    // Mettre à jour une catégorie
    public CategoryDto updateCategory(UUID id, CategoryDto updatedCategoryDto) {
        return categoryRepository.findById(id).map(existingCategory -> {
            if (!existingCategory.getNomCategory().equals(updatedCategoryDto.getNomCategory()) &&
                    categoryRepository.existsByNomCategory(updatedCategoryDto.getNomCategory())) {
                throw new IllegalArgumentException("Ce nom de catégorie est déjà utilisé");
            }
            existingCategory.setNomCategory(updatedCategoryDto.getNomCategory());
            return convertToDto(categoryRepository.save(existingCategory));
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