package com.hinkaku.product_manager.DTO;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class departementDTO {

    private UUID id;

    private String nom;
}
