package com.hinkaku.product_manager.repository;

import com.hinkaku.product_manager.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, UUID> {
    Optional<Departement> findByNom(String nom);
    boolean existsByNom(String nom);
}
