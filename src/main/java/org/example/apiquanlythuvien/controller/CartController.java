package org.example.apiquanlythuvien.controller;

import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.AddToCartRequest;
import org.example.apiquanlythuvien.data.response.CartResponse;
import org.example.apiquanlythuvien.service.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

  private final CartService cartService;

  @PostMapping("/add")
  public ResponseEntity<?> addToCart(@RequestBody AddToCartRequest request) {
    try {
      cartService.addToCart(request.getBanSaoSachId());
      return ResponseEntity.ok("Thêm vào giỏ thành công");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<List<CartResponse>> getCart() {
    return ResponseEntity.ok(cartService.getCart());
  }

  @DeleteMapping("/remove")
  public ResponseEntity<?> removeFromCart(@RequestParam Long banSaoSachId) {
    cartService.removeFromCart(banSaoSachId);
    return ResponseEntity.ok("Xóa khỏi giỏ thành công");
  }

  @DeleteMapping("/clearAll")
  public ResponseEntity<?> clearCart() {
    cartService.clearCart();
    return ResponseEntity.ok("Xóa giỏ thành công");
  }
}
