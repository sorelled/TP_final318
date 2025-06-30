package com.hinkaku.product_manager.repository;

import com.hinkaku.product_manager.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, UUID> {

    // Trouver un département par son nom exact
    Optional<Departement> findByNom(String nom);

    // Vérifier si un département existe par nom
    boolean existsByNom(String nom);
}
