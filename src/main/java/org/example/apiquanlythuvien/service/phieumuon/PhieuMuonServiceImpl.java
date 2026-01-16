package org.example.apiquanlythuvien.service.phieumuon;

import lombok.RequiredArgsConstructor;
import org.example.apiquanlythuvien.data.entity.*;
import org.example.apiquanlythuvien.data.request.CreatePhieuMuonRequest;
import org.example.apiquanlythuvien.data.response.ChiTietMuonTraResponse;
import org.example.apiquanlythuvien.data.response.PhieuMuonResponse;
import org.example.apiquanlythuvien.defaults.Const;
import org.example.apiquanlythuvien.exception.NotFoundException;
import org.example.apiquanlythuvien.mapper.ChiTietMuonTraMapper;
import org.example.apiquanlythuvien.mapper.PhieuMuonMapper;
import org.example.apiquanlythuvien.responsitory.*;
import org.example.apiquanlythuvien.service.cart.CartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PhieuMuonServiceImpl implements PhieuMuonService {

  private final PhieuMuonRepository phieuMuonRepository;
  private final BanSaoSachRepository banSaoSachRepository;
  private final AccountRepository accountRepository;
  private final CartService cartService;
  private final PhieuMuonMapper phieuMuonMapper;
  private final ChiTietMuonTraRepository chiTietMuonTraRepository;
  private final ChiTietMuonTraMapper chiTietMuonTraMapper;

  @Override
  @Transactional
  public void createPhieuMuon(CreatePhieuMuonRequest request) {
    // Kiểm tra danh sách ID
    List<Long> selectedIds = request.getSelectedBanSaoSachIds();
    if (selectedIds == null || selectedIds.isEmpty()) {
      throw new NotFoundException("Chưa chọn sách để mượn");
    }

    // Lấy username từ Security Context
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new RuntimeException("Chưa đăng nhập");
    }

    String username = authentication.getName();

    // Truy ra Account từ username
    Account account = accountRepository.findByUsername(username)
        .orElseThrow(() -> new NotFoundException("Không tìm thấy tài khoản"));

    DocGia docGia = account.getDocGia();
    if (docGia == null) {
      throw new NotFoundException("Tài khoản chưa có thông tin độc giả");
    }

    TheThuVien theThuVien = docGia.getTheThuVien();
    if (theThuVien == null) {
      throw new NotFoundException("Độc giả chưa có thẻ thư viện");
    }

    // Kiểm tra trạng thái thẻ thư viện
    if (!Const.THETHUVIEN_ACTIVE.equals(theThuVien.getTrangThai())) {
      throw new RuntimeException("Thẻ thư viện không hoạt động");
    }

    // Lấy danh sách bản sao sách từ database
    List<BanSaoSach> banSaoSachList = banSaoSachRepository.findAllById(selectedIds);

    if (banSaoSachList.size() != selectedIds.size()) {
      throw new NotFoundException("Một số bản sao sách không tồn tại");
    }

    // Kiểm tra trạng thái khả dụng
    for (BanSaoSach banSao : banSaoSachList) {
      if (!Const.BANSACH_AVAILABLE.equals(banSao.getTrangThaiBanSaoSach())) {
        throw new NotFoundException(
            "Bản sao sách ID " + banSao.getBanSaoSachId() + " không khả dụng");
      }
    }

    // Tạo phiếu mượn
    PhieuMuon phieuMuon = new PhieuMuon();
    phieuMuon.setTheThuVien(theThuVien);
    phieuMuon.setNgayMuon(new Date());
    phieuMuon.setTrangThaiPhieuMuon(Const.PHIEUMUON_PENDING);

    // Tính ngày hẹn trả (14 ngày sau)
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.DAY_OF_MONTH, 14);
    Date hanTra = calendar.getTime();

    // Tạo danh sách chi tiết phiếu mượn
    List<ChiTietMuonTra> chiTietList = new ArrayList<>();

    for (BanSaoSach banSao : banSaoSachList) {
      ChiTietMuonTra chiTiet = new ChiTietMuonTra();
      chiTiet.setPhieuMuon(phieuMuon);
      chiTiet.setBanSaoSach(banSao);
      chiTiet.setHanTra(hanTra);
      chiTiet.setNgayTra(null);
      chiTiet.setTinhTrangKhiTra(Const.PHIEUMUON_CT_PENDING);
      chiTiet.setTienPhat(BigDecimal.ZERO);

      chiTietList.add(chiTiet);

      // Cập nhật trạng thái bản sao sách
      banSao.setTrangThaiBanSaoSach(Const.BANSACH_BORROWED);
    }

    phieuMuon.setChiTietMuonTra(chiTietList);

    phieuMuonRepository.save(phieuMuon);

  }

  @Override
  public Page<PhieuMuonResponse> getPhieuMuonByTrangThai(String trangThai, Pageable pageable) {

    // Lấy username từ Security Context
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new RuntimeException("Chưa đăng nhập");
    }

    String username = authentication.getName();

    // Truy ra Account từ username
    Account account = accountRepository.findByUsername(username)
        .orElseThrow(() -> new NotFoundException("Không tìm thấy tài khoản"));

    DocGia docGia = account.getDocGia();
    if (docGia == null) {
      throw new NotFoundException("Tài khoản chưa có thông tin độc giả");
    }

    TheThuVien theThuVien = docGia.getTheThuVien();
    if (theThuVien == null) {
      throw new NotFoundException("Độc giả chưa có thẻ thư viện");
    }

    Page<PhieuMuon> phieuMuonPage = phieuMuonRepository.findByTheThuVienIdAndTrangThai(
        theThuVien.getTheThuVienId(), trangThai, pageable);
    return phieuMuonPage.map(phieuMuonMapper::toResponseMapper);
  }

  @Override
  public List<ChiTietMuonTraResponse> getChiTietMuonTraByPhieuMuonId(Long phieuMuonId) {
    List<ChiTietMuonTra> list = chiTietMuonTraRepository.findByPhieuMuonPhieuMuonId(phieuMuonId);
    return list.stream()
        .map(chiTietMuonTraMapper::toResponseMapper)
        .toList();
  }
}