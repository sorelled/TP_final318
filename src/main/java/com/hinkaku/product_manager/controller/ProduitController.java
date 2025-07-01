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

    // Liste des produits
    @GetMapping
    public String listProduits(Model model, @RequestParam(value = "success", required = false) String successMsg) {
        List<ProduitDto> produits = produitService.getAllProduits();
        model.addAttribute("produits", produits);
        if (successMsg != null) {
            model.addAttribute("msg", successMsg);
        }
        return "produits/list";
    }

    // Formulaire création
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("produit", new ProduitDto());
        return "produits/create";
    }

    // Traitement création
    @PostMapping("/create")
    public String createProduit(@Valid @ModelAttribute("produit") ProduitDto produitDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "produits/create";
        }
        produitService.addProduit(produitDto);
        redirectAttributes.addAttribute("success", "Produit créé avec succès !");
        return "redirect:/produits";
    }

    // Formulaire édition
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes) {
        ProduitDto produit = produitService.getProduitById(id);
        if (produit == null) {
            redirectAttributes.addAttribute("error", "Produit non trouvé");
            return "redirect:/produits";
        }
        model.addAttribute("produit", produit);
        return "produits/edit";
    }

    // Traitement édition
    @PostMapping("/{id}/edit")
    public String updateProduit(@PathVariable UUID id,
                                @Valid @ModelAttribute("produit") ProduitDto produitDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "produits/edit";
        }
        produitService.updateProduit(id, produitDto);
        redirectAttributes.addAttribute("success", "Produit modifié avec succès !");
        return "redirect:/produits";
    }

    // Suppression via POST
    @PostMapping("/{id}/delete")
    public String deleteProduit(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        produitService.deleteProduit(id);
        redirectAttributes.addAttribute("success", "Produit supprimé avec succès !");
        return "redirect:/produits";
    }
}






//package com.hinkaku.product_manager.controller;



//
//import com.hinkaku.product_manager.dto.ProduitDto;
//import com.hinkaku.product_manager.service.ProduitService;
//import jakarta.validation.Valid;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@Controller
//@RequestMapping("/produits")
//public class ProduitController {
//
//    private final ProduitService produitService;
//
//    public ProduitController(ProduitService produitService) {
//        this.produitService = produitService;
//    }
//
//    // Affichage de la liste des produits
//    @GetMapping
//    public String listProduits(Model model, @RequestParam(required = false) String error) {
//        List<ProduitDto> produits = produitService.getAllProduits();
//        model.addAttribute("produits", produits);
//        model.addAttribute("error", error);
//        return "produits/list";  // templates/produits/list.html
//    }
//
//    // Formulaire création
//    @GetMapping("/create")
//    public String showCreateForm(Model model) {
//        model.addAttribute("produit", new ProduitDto());
//        return "produits/create"; // templates/produits/create.html
//    }
//
//    // Traitement création avec validation
//    @PostMapping
//    public String createProduit(@Valid @ModelAttribute("produit") ProduitDto produitDto,
//                                BindingResult result,
//                                Model model) {
//        if (result.hasErrors()) {
//            return "produits/create";
//        }
//        produitService.addProduit(produitDto);
//        return "redirect:/produits";
//    }
//
//    // Formulaire édition
//    @GetMapping("/{id}/edit")
//    public String showEditForm(@PathVariable UUID id, Model model) {
//        ProduitDto produit = produitService.getProduitById(id);
//        if (produit == null) {
//            return "redirect:/produits?error=ProduitNonTrouve";
//        }
//        model.addAttribute("produit", produit);
//        return "produits/edit"; // templates/produits/edit.html
//    }
//
//    // Traitement édition avec validation
//    @PostMapping("/{id}/edit")
//    public String updateProduit(@PathVariable UUID id,
//                                @Valid @ModelAttribute("produit") ProduitDto produitDto,
//                                BindingResult result,
//                                Model model) {
//        if (result.hasErrors()) {
//            return "produits/edit";
//        }
//        produitService.updateProduit(id, produitDto);
//        return "redirect:/produits";
//    }
//
//    // Suppression (idéalement via POST ou DELETE, ici GET pour simplicité)
//    @GetMapping("/{id}/delete")
//    public String deleteProduit(@PathVariable UUID id) {
//        produitService.deleteProduit(id);
//        return "redirect:/produits";
//    }
//}
