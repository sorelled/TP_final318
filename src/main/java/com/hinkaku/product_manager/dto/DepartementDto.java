package com.hinkaku.product_manager.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartementDto {

    private UUID id;

    private String nom;
}
