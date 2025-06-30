package com.hinkaku.product_manager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hinkaku.product_manager.dto.ProduitDto;
import com.hinkaku.product_manager.model.Category;
import com.hinkaku.product_manager.model.Produit;
import com.hinkaku.product_manager.repository.CategoryRepository;
import com.hinkaku.product_manager.repository.ProduitRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final CategoryRepository categoryRepository;

    // Ajouter un nouveau produit
    public ProduitDto addProduit(ProduitDto dto) {
        Produit produit = mapToEntity(dto);
        validateProduit(produit);
        Produit savedProduit = produitRepository.save(produit);
        return mapToDTO(savedProduit);
    }

    // Récupérer tous les produits
    public List<ProduitDto> getAllProduits() {
        return produitRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Récupérer un produit par son ID
    public ProduitDto getProduitById(UUID id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé !"));
        return mapToDTO(produit);
    }

    // Récupérer les produits par catégorie (version adaptée)
    public List<ProduitDto> getProduitsByCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée"));

        return produitRepository.findByCategory(category)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    // Mettre à jour un produit
    public ProduitDto updateProduit(UUID id, ProduitDto dto) {
        return produitRepository.findById(id).map(existingProduit -> {
            Produit updatedProduit = mapToEntity(dto);
            updatedProduit.setIdProduit(id);
            validateProduit(updatedProduit);

            Produit savedProduit = produitRepository.save(updatedProduit);
            return mapToDTO(savedProduit);
        }).orElseThrow(() -> new RuntimeException("Produit non trouvé !"));
    }

    // Supprimer un produit
    public void deleteProduit(UUID id) {
        if (!produitRepository.existsById(id)) {
            throw new EntityNotFoundException("Produit non trouvé !");
        }
        produitRepository.deleteById(id);
    }

    // Validation des données du produit
    private void validateProduit(Produit produit) {
        if (produit.getDateExpiration().isBefore(LocalDate.now().plusDays(7))) {
            throw new IllegalArgumentException("La date d'expiration doit être au moins 7 jours dans le futur");
        }
    }

    // Conversion DTO vers Entité
    private Produit mapToEntity(ProduitDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée"));


        return Produit.builder()
                .idProduit(dto.getIdProduit())
                .nomProduit(dto.getNomProduit())
                .prixProduit(dto.getPrixProduit())
                .dateExpiration(dto.getDateExpiration())
                .category(category)
                .build();
    }

    // Conversion Entité vers DTO
    private ProduitDto mapToDTO(Produit produit) {
        return ProduitDto.builder()
                .idProduit(produit.getIdProduit())
                .nomProduit(produit.getNomProduit())
                .prixProduit(produit.getPrixProduit())
                .dateExpiration(produit.getDateExpiration())
                .categoryId(produit.getCategory().getIdCategory())
                .build();
    }
}