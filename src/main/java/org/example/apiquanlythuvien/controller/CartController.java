package org.example.apiquanlythuvien.controller;

import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.CartViewRequest;
import org.example.apiquanlythuvien.data.response.CartResponse;
import org.example.apiquanlythuvien.service.cart.CartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/view")
    public ResponseEntity<Page<CartResponse>> getCart(
            @RequestBody CartViewRequest request,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<CartResponse> cart = cartService.getCart(request, pageable);
        return ResponseEntity.ok(cart);
    }
}