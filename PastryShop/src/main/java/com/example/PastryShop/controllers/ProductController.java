package com.example.PastryShop.controllers;

import com.example.PastryShop.models.Product;
import com.example.PastryShop.repositories.ProductRepository;
import com.example.PastryShop.services.CartService;
import com.example.PastryShop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
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

    @PostMapping("/create")
    public @ResponseBody Object createProduct(@RequestParam Long id,
                                           @RequestParam String title,
                                           @RequestParam String description,
                                              @RequestParam int price){
        Product product = new Product(id, title, description, price);
        return productRepository.save(product);
    }

    @DeleteMapping("/product/delete/{id}")
    public @ResponseBody void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @GetMapping
    public @ResponseBody List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/product/addToCart/{id}")
    public String addToCart(@PathVariable Long id,
                            @RequestParam(name = "quantity", required = true)
                            int quantity){
        Product product = productService.getProductById(id);
//        if (product == null) {
//            // handle error
//            return "redirect:/";
//        }
        cartService.addToCart(id, quantity);
        return "redirect:/";
    }
}
