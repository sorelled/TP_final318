package com.hinkaku.product_manager.controller;

import com.hinkaku.product_manager.dto.ProduitDto;
import com.hinkaku.product_manager.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public String listProduits(Model model) {
        model.addAttribute("produits", produitService.getAllProduits());
        return "produits/list";
    }

    @GetMapping("/add")
    public String showAddProduitForm(Model model) {
        model.addAttribute("produitDto", new ProduitDto());
        return "produits/add";
    }

    @GetMapping("/{id}")
    public String getProduitById(@PathVariable UUID id, Model model) {
        model.addAttribute("produit", produitService.getProduitById(id));
        return "produits/view";
    }

    @PostMapping("/save")
    public String saveProduit(@ModelAttribute("produitDto") ProduitDto produitDto) {
        produitService.addProduit(produitDto);
        return "redirect:/produits";
    }

    @GetMapping("/{id}/edit")
    public String showEditProduitForm(@PathVariable UUID id, Model model) {
        model.addAttribute("produitDto", produitService.getProduitById(id));
        return "produits/edit";
    }

    @PostMapping("/{id}/update")
    public String updateProduit(@PathVariable UUID id, @ModelAttribute("produitDto") ProduitDto produitDto) {
        produitService.updateProduit(id, produitDto);
        return "redirect:/produits";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduit(@PathVariable UUID id) {
        produitService.deleteProduit(id);
        return "redirect:/produits";
    }
}
