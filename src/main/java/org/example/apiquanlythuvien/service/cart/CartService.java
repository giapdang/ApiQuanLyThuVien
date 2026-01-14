package org.example.apiquanlythuvien.service.cart;

import java.util.List;
import org.example.apiquanlythuvien.data.response.CartResponse;

public interface CartService {

  void addToCart(Long banSaoSachId);
  List<CartResponse> getCart();
  void removeFromCart(Long banSaoSachId);
  void clearCart();
}
