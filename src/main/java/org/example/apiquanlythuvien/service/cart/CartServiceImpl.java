package org.example.apiquanlythuvien.service.cart;

import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.entity.BanSaoSach;
import org.example.apiquanlythuvien.data.request.CartViewRequest;
import org.example.apiquanlythuvien.data.response.CartResponse;
import org.example.apiquanlythuvien.data.response.TacGiaResponse;
import org.example.apiquanlythuvien.exception.NotFoundException;
import org.example.apiquanlythuvien.responsitory.BanSaoSachRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final BanSaoSachRepository banSaoSachRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<CartResponse> getCart(CartViewRequest request, Pageable pageable) {
        List<Long> banSaoSachIds = request.getSelectedBanSaoSachIds();

        // Nếu danh sách rỗng, trả về trang trống
        if (banSaoSachIds == null || banSaoSachIds.isEmpty()) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }

        // Lấy tất cả bản sao sách theo IDs
        List<BanSaoSach> allBanSaoSach = banSaoSachRepository.findAllById(banSaoSachIds);

        // Kiểm tra nếu có ID không tồn tại
        if (allBanSaoSach.size() != banSaoSachIds.size()) {
            List<Long> foundIds = allBanSaoSach.stream()
                .map(BanSaoSach::getBanSaoSachId)
                .collect(Collectors.toList());

            List<Long> notFoundIds = banSaoSachIds.stream()
                .filter(id -> !foundIds.contains(id))
                .collect(Collectors.toList());

            throw new NotFoundException("Không tìm thấy bản sao sách với IDs: " + notFoundIds);
        }

        // Giữ nguyên thứ tự từ request
        List<BanSaoSach> sortedBanSaoSach = banSaoSachIds.stream()
            .map(id -> allBanSaoSach.stream()
                .filter(b -> b.getBanSaoSachId().equals(id))
                .findFirst()
                .orElse(null))
            .filter(b -> b != null)
            .collect(Collectors.toList());

        // Tính toán pagination thủ công
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedBanSaoSach.size());

        List<CartResponse> pageContent;
        if (start >= sortedBanSaoSach.size()) {
            pageContent = new ArrayList<>();
        } else {
            pageContent = sortedBanSaoSach.subList(start, end).stream()
                .map(this::convertToCartResponse)
                .collect(Collectors.toList());
        }

        return new PageImpl<>(pageContent, pageable, sortedBanSaoSach.size());
    }

    private CartResponse convertToCartResponse(BanSaoSach banSaoSach) {
        CartResponse response = new CartResponse();
        response.setBanSaoSachId(banSaoSach.getBanSaoSachId());
        response.setTinhTrangBanSaoSach(banSaoSach.getTinhTrangBanSaoSach());

        response.setSachId(banSaoSach.getSach().getSachId());
        response.setTenSach(banSaoSach.getSach().getTenSach());
        response.setAnhBia(banSaoSach.getSach().getAnhBia());

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