package com.hinkaku.product_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "products")
public class Category {

    @Id
    @GeneratedValue
    private UUID idCategory;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Le nom de la catégorie est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    private String nomCategory;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Produit> products;
}