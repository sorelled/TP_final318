package com.hinkaku.product_manager.controller;

import com.hinkaku.product_manager.dto.ProduitDto;
import com.hinkaku.product_manager.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    // 1. Ajout du titre de page dans le modèle
    @GetMapping("")
    public String listProduits(Model model, @ModelAttribute("msg") String msg) {
        List<ProduitDto> produits = produitService.getAllProduits();
        model.addAttribute("produits", produits);
        model.addAttribute("titrePage", "Liste des Produits"); // Ajouté

        if (msg != null && !msg.isEmpty()) {
            model.addAttribute("msg", msg);
        }

        return "produits/list";
    }

    // 2. Correction du chemin de redirection
    @PostMapping("/create")
    public String createProduit(@Valid @ModelAttribute("produit") ProduitDto produitDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.produit", bindingResult);
            redirectAttributes.addFlashAttribute("produit", produitDto);
            return "redirect:/produits/create";
        }
        produitService.addProduit(produitDto);
        redirectAttributes.addFlashAttribute("msg", "Produit créé avec succès !");
        return "redirect:/produits"; // Corrigé le chemin
    }

    // 3. Ajout du titre de page pour l'édition
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes) {
        if (!model.containsAttribute("produit")) {
            ProduitDto produit = produitService.getProduitById(id);
            if (produit == null) {
                redirectAttributes.addFlashAttribute("msg", "Produit non trouvé");
                return "redirect:/produits";
            }
            model.addAttribute("produit", produit);
        }
        model.addAttribute("titrePage", "Modifier Produit"); // Ajouté
        return "produits/edit";
    }

    // 4. Correction du chemin de redirection
    @PostMapping("/{id}/edit")
    public String updateProduit(@PathVariable UUID id,
                                @Valid @ModelAttribute("produit") ProduitDto produitDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.produit", bindingResult);
            redirectAttributes.addFlashAttribute("produit", produitDto);
            return "redirect:/produits/" + id + "/edit";
        }
        produitService.updateProduit(id, produitDto);
        redirectAttributes.addFlashAttribute("msg", "Produit modifié avec succès !");
        return "redirect:/produits"; // Corrigé le chemin
    }

    // 5. Correction du chemin de redirection
    @PostMapping("/{id}/delete")
    public String deleteProduit(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        produitService.deleteProduit(id);
        redirectAttributes.addFlashAttribute("msg", "Produit supprimé avec succès !");
        return "redirect:/produits"; // Corrigé le chemin
    }
}