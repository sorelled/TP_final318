package com.hinkaku.product_manager.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDto {

    private UUID idProduit;

    @NotBlank(message = "Le nom du produit est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nomProduit;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private Integer prixProduit;

    @NotNull(message = "La date d'expiration est obligatoire")
    @Future(message = "La date d'expiration doit être dans le futur")
    private LocalDate dateExpiration;

    @NotNull(message = "La catégorie est obligatoire")
    private UUID categoryId;

    @NotNull(message = "Le département est obligatoire")
    private UUID departementId;

    @AssertTrue(message = "La date d'expiration doit être au moins 7 jours dans le futur")
    public boolean isDateExpirationValid() {
        if (dateExpiration == null) return false;
        return dateExpiration.isAfter(LocalDate.now().plusDays(7));
    }
}