package com.hinkaku.product_manager.DTO;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategorieDTO {

    private UUID idCategory;

    private String nomCategory;
}
