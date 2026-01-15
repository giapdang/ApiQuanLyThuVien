package org.example.apiquanlythuvien.service.cart;

import org.example.apiquanlythuvien.data.request.CartViewRequest;
import org.example.apiquanlythuvien.data.response.CartResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService {
    Page<CartResponse> getCart(CartViewRequest request, Pageable pageable);
}