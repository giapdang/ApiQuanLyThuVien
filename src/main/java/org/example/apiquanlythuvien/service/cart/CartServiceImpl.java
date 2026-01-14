package org.example.apiquanlythuvien.service.cart;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.response.CartResponse;
import org.example.apiquanlythuvien.data.response.TacGiaResponse;
import org.example.apiquanlythuvien.data.entity.BanSaoSach;
import org.example.apiquanlythuvien.defaults.Const;
import org.example.apiquanlythuvien.exception.NotFoundException;
import org.example.apiquanlythuvien.responsitory.BanSaoSachRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private static final String CART_SESSION_KEY = "CART";
    private final HttpSession httpSession;
    private final BanSaoSachRepository banSaoSachRepository;

    @Override
    public void addToCart(Long banSaoSachId) {

        BanSaoSach banSaoSach = banSaoSachRepository.findById(banSaoSachId)
            .orElseThrow(() -> new NotFoundException("Bản sao sách không tồn tại"));

        // Kiểm tra trạng thái bản sao sách
        if (Const.BANSACH_BORROWED.equals(banSaoSach.getTinhTrangBanSaoSach())) {
            throw new IllegalStateException("Bản sao sách đã được mượn, không thể thêm vào giỏ");
        }

        if (!banSaoSachRepository.existsById(banSaoSachId)) {
            throw new NotFoundException("Bản sao sách không tồn tại");
        }


        List<Long> cart = getCartIds();

        if (cart.contains(banSaoSachId)) {
            throw new NotFoundException("Bản sao sách đã có trong giỏ");
        }

        cart.add(banSaoSachId);
        httpSession.setAttribute(CART_SESSION_KEY, cart);
    }

    @Override
    public List<CartResponse> getCart() {
        List<Long> cartIds = getCartIds();

        if (cartIds.isEmpty()) {
            return new ArrayList<>();
        }

        return cartIds.stream()
            .map(banSaoSachRepository::findById)
            .filter(opt -> opt.isPresent())
            .map(opt -> opt.get())
            .map(this::convertToCartResponse)
            .collect(Collectors.toList());
    }

    @Override
    public void removeFromCart(Long banSaoSachId) {
        List<Long> cart = getCartIds();
        cart.remove(banSaoSachId);
        httpSession.setAttribute(CART_SESSION_KEY, cart);
    }

    @Override
    public void clearCart() {
        httpSession.removeAttribute(CART_SESSION_KEY);
    }

    @SuppressWarnings("unchecked")
    private List<Long> getCartIds() {
        List<Long> cart = (List<Long>) httpSession.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            httpSession.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    private CartResponse convertToCartResponse(BanSaoSach banSaoSach) {
        CartResponse response = new CartResponse();
        response.setBanSaoSachId(banSaoSach.getBanSaoSachId());
        response.setTinhTrangBanSaoSach(banSaoSach.getTinhTrangBanSaoSach());

        // Thông tin từ Sach
        response.setSachId(banSaoSach.getSach().getSachId());
        response.setTenSach(banSaoSach.getSach().getTenSach());
        response.setAnhBia(banSaoSach.getSach().getAnhBia());

        // Danh sách tác giả
        List<TacGiaResponse> tacGiaList = banSaoSach.getSach().getSachTacGia()
            .stream()
            .map(sachTacGia -> {
                TacGiaResponse tacGiaResponse = new TacGiaResponse();
                tacGiaResponse.setTacGiaId(sachTacGia.getTacGia().getTacGiaId());
                tacGiaResponse.setTenTacGia(sachTacGia.getTacGia().getTenTacGia());
                return tacGiaResponse;
            })
            .collect(Collectors.toList());
        response.setTacGiaList(tacGiaList);

        return response;
    }
}
