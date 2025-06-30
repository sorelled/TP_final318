package com.hinkaku.product_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "produits")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue
    private UUID idProduit;

    @Column(nullable = false)
    @NotBlank(message = "Le nom du produit est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nomProduit;

    @Column(nullable = false)
    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private Integer prixProduit;

    @Column(nullable = false)
    @Future(message = "La date d'expiration doit être dans le futur")
    private LocalDate dateExpiration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

    @AssertTrue(message = "La date d'expiration doit être au moins 7 jours dans le futur")
    public boolean isDateExpirationValid() {
        if (dateExpiration == null) return false;
        return dateExpiration.isAfter(LocalDate.now().plusDays(7));
    }
}