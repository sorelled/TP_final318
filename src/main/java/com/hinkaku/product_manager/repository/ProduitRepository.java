package com.hinkaku.product_manager.repository;

import com.hinkaku.product_manager.model.Produit;
import com.hinkaku.product_manager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, UUID> {
    List<Produit> findByNomProduit(String nomProduit);
    List<Produit> findByNomProduitContainingIgnoreCase(String keyword);
    List<Produit> findByCategory(Category category);
    List<Produit> findByDateExpirationBefore(LocalDate date);
    List<Produit> findByDateExpirationAfter(LocalDate date);
}
