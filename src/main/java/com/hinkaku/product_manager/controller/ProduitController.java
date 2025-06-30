package com.hinkaku.product_manager.controller;

import com.hinkaku.product_manager.dto.ProduitDto;
import com.hinkaku.product_manager.service.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public ResponseEntity<List<ProduitDto>> getAllProduits() {
        return ResponseEntity.ok(produitService.getAllProduits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitDto> getProduitById(@PathVariable UUID id) {
        return ResponseEntity.ok(produitService.getProduitById(id));
    }

    @PostMapping
    public ResponseEntity<ProduitDto> createProduit(@RequestBody ProduitDto produitDto) {
        return ResponseEntity.ok(produitService.addProduit(produitDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDto> updateProduit(@PathVariable UUID id, @RequestBody ProduitDto produitDto) {
        return ResponseEntity.ok(produitService.updateProduit(id, produitDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable UUID id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
}
