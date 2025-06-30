package com.hinkaku.product_manager.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategorieDto {

    private UUID idCategory;

    private String nomCategory;
}
