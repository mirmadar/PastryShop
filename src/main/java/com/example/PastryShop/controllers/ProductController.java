package com.example.PastryShop.controllers;

import com.example.PastryShop.models.Product;
import com.example.PastryShop.services.CartService;
import com.example.PastryShop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final CartService cartService;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Model model){
        model.addAttribute("products", productService.listProducts(title));
        return "products";
    }

//    @GetMapping("/product/{id}")
//    public String productInfo(@PathVariable Long id, Model model){
//        model.addAttribute("product", productService.getProductById(id));
//        return "product-info";
//    }

//    @PostMapping("/product/delete/{id}")
//    public String deleteProduct(@PathVariable Long id){
//        productService.deleteProduct(id);
//        return "redirect:/";
//    }

    @PostMapping("/product/addToCart/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam(name = "quantity", required = true) int quantity){
        Product product = productService.getProductById(id);
//        if (product == null) {
//            // handle error
//            return "redirect:/";
//        }
        cartService.addToCart(id, quantity);
        return "redirect:/";
    }
}
