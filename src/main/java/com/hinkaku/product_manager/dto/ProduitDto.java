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

    @Future(message = "La date d'expiration doit être dans le futur")
    private LocalDate dateExpiration;

    @NotNull(message = "La catégorie est obligatoire")
    private UUID categoryId; // on stocke juste l'ID de la catégorie

}
