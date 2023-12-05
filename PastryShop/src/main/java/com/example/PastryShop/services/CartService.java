package com.example.PastryShop.services;

import com.example.PastryShop.models.Cart;
import com.example.PastryShop.models.CartItem;
import com.example.PastryShop.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final Cart cart = new Cart();
    private final ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public void addToCart(Long productId, int quantity) {
        Product product = productService.getProductById(productId);
        CartItem cartItem = new CartItem(product, quantity);
        cart.addItem(cartItem);
    }

    public void removeFromCart(Long productId) {
        List<CartItem> items = cart.getItems();
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(productId)) {
                cart.removeItem(item);
                break;
            }
        }
    }

    public Cart getCart() {
        return cart;
    }
}
