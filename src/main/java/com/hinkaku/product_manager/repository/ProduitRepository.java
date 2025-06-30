package com.hinkaku.product_manager.repository;

import com.hinkaku.product_manager.model.Produit;
import com.hinkaku.product_manager.model.Category;
import com.hinkaku.product_manager.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, UUID> {

    // Rechercher les produits par nom exact
    List<Produit> findByNomProduit(String nomProduit);

    // Rechercher les produits dont le nom contient un mot-clé (insensible à la casse)
    List<Produit> findByNomProduitContainingIgnoreCase(String keyword);

    // Rechercher les produits par catégorie
    List<Produit> findByCategory(Category category);

    // Rechercher les produits par département
    List<Produit> findByDepartement(Departement departement);

    // Rechercher les produits expirant avant une certaine date
    List<Produit> findByDateExpirationBefore(LocalDate date);

    // Rechercher les produits expirant après une certaine date
    List<Produit> findByDateExpirationAfter(LocalDate date);
}
