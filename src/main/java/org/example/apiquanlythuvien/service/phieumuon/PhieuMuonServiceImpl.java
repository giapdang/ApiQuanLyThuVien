package org.example.apiquanlythuvien.service.phieumuon;

import lombok.RequiredArgsConstructor;
import org.example.apiquanlythuvien.data.entity.*;
import org.example.apiquanlythuvien.data.request.CreatePhieuMuonRequest;
import org.example.apiquanlythuvien.data.response.ChiTietMuonTraResponse;
import org.example.apiquanlythuvien.data.response.DocGiaFromChiTietResponse;
import org.example.apiquanlythuvien.data.response.PhieuMuonResponse;
import org.example.apiquanlythuvien.defaults.Const;
import org.example.apiquanlythuvien.exception.BadRequestException;
import org.example.apiquanlythuvien.exception.ForbiddenException;
import org.example.apiquanlythuvien.exception.NotFoundException;
import org.example.apiquanlythuvien.mapper.ChiTietMuonTraMapper;
import org.example.apiquanlythuvien.mapper.DocGiaMapper;
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
  private final DocGiaMapper docGiaMapper;

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
      throw new BadRequestException("Chưa đăng nhập");
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

    // Kiểm tra trạng thái độc giả và thẻ thư viện
    if (Const.DOCGIA_INACTIVE.equals(docGia.getTrangThaiDocGia()) ||
        !Const.THETHUVIEN_ACTIVE.equals(theThuVien.getTrangThai())) {
      throw new ForbiddenException("Độc giả hoặc thẻ thư viện đã bị vô hiệu hóa, không thể mượn sách");
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
      throw new BadRequestException("Chưa đăng nhập");
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
  public Page<PhieuMuonResponse> getAllPhieuMuon(String trangThai, Pageable pageable) {
    Page<PhieuMuon> phieuMuonPage = phieuMuonRepository.findByTrangThai(trangThai, pageable);
    return phieuMuonPage.map(phieuMuonMapper::toResponseMapper);
  }

  @Override
  public List<ChiTietMuonTraResponse> getChiTietMuonTraByPhieuMuonId(Long phieuMuonId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new BadRequestException("Chưa đăng nhập");
    }

    boolean isAdmin = authentication.getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals(Const.ROLE_ADMIN));

    if (!isAdmin) {
      String username = authentication.getName();
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

      PhieuMuon phieuMuon = phieuMuonRepository.findById(phieuMuonId)
          .orElseThrow(() -> new NotFoundException("Không tìm thấy phiếu mượn"));

      if (!phieuMuon.getTheThuVien().getTheThuVienId().equals(theThuVien.getTheThuVienId())) {
        throw new ForbiddenException("Không có quyền truy cập phiếu mượn này");
      }
    }

    List<ChiTietMuonTra> list = chiTietMuonTraRepository.findByPhieuMuonPhieuMuonId(phieuMuonId);
    return list.stream()
        .map(chiTietMuonTraMapper::toResponseMapper)
        .toList();
  }

  @Override
  @Transactional
  public void updatePhieuMuonStatus(Long phieuMuonId, String status) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new BadRequestException("Chưa đăng nhập");
    }

    boolean isAdmin = authentication.getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals(Const.ROLE_ADMIN));

    if (!isAdmin) {
      throw new ForbiddenException("Không có quyền thực hiện thao tác này");
    }

    PhieuMuon phieuMuon = phieuMuonRepository.findById(phieuMuonId)
        .orElseThrow(() -> new NotFoundException("Không tìm thấy phiếu mượn"));

    // Cascading logic based on new status
    List<ChiTietMuonTra> chiTietList = chiTietMuonTraRepository
        .findByPhieuMuonPhieuMuonId(phieuMuonId);

    switch (status) {
      case Const.PHIEUMUON_PENDING: // DANG_CHO
        for (ChiTietMuonTra chiTiet : chiTietList) {
          chiTiet.setTinhTrangKhiTra(Const.PHIEUMUON_CT_PENDING);
          chiTiet.setNgayTra(null);
          chiTiet.getBanSaoSach().setTrangThaiBanSaoSach(Const.BANSACH_BORROWED);
          chiTietMuonTraRepository.save(chiTiet);
          banSaoSachRepository.save(chiTiet.getBanSaoSach());
        }
        break;

      case Const.PHIEUMUON_BORROWED: // DANG_MUON
        for (ChiTietMuonTra chiTiet : chiTietList) {
          chiTiet.setTinhTrangKhiTra(Const.PHIEUMUON_CT_BORROWED);
          chiTiet.setNgayTra(null);
          chiTiet.getBanSaoSach().setTrangThaiBanSaoSach(Const.BANSACH_BORROWED);
          chiTietMuonTraRepository.save(chiTiet);
          banSaoSachRepository.save(chiTiet.getBanSaoSach());
        }
        break;

      case Const.PHIEUMUON_CANCELLED: // HUY
        for (ChiTietMuonTra chiTiet : chiTietList) {
          chiTiet.setTinhTrangKhiTra(Const.PHIEUMUON_CT_CANCELLED);
          chiTiet.setNgayTra(null);
          chiTiet.getBanSaoSach().setTrangThaiBanSaoSach(Const.BANSACH_AVAILABLE);
          chiTietMuonTraRepository.save(chiTiet);
          banSaoSachRepository.save(chiTiet.getBanSaoSach());
        }
        break;

      case Const.PHIEUMUON_OVERDUE: // QUA_HAN
        for (ChiTietMuonTra chiTiet : chiTietList) {
          chiTiet.setTinhTrangKhiTra(Const.PHIEUMUON_CT_OVERDUE);
          chiTiet.setNgayTra(null);
          chiTiet.getBanSaoSach().setTrangThaiBanSaoSach(Const.BANSACH_BORROWED);
          chiTietMuonTraRepository.save(chiTiet);
          banSaoSachRepository.save(chiTiet.getBanSaoSach());
        }
        break;

      case Const.PHIEUMUON_COMPLETED: // HOAN_TAT
        for (ChiTietMuonTra chiTiet : chiTietList) {
          chiTiet.setTinhTrangKhiTra(Const.PHIEUMUON_CT_RETURNED);
          chiTiet.setNgayTra(new Date());
          chiTiet.getBanSaoSach().setTrangThaiBanSaoSach(Const.BANSACH_AVAILABLE);
          chiTietMuonTraRepository.save(chiTiet);
          banSaoSachRepository.save(chiTiet.getBanSaoSach());
        }
        break;

      default:
        break;
    }

    phieuMuon.setTrangThaiPhieuMuon(status);
    phieuMuonRepository.save(phieuMuon);
  }

  @Override
  @Transactional
  public void updateChiTietStatus(Long chiTietId, String status) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new BadRequestException("Chưa đăng nhập");
    }

    boolean isAdmin = authentication.getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals(Const.ROLE_ADMIN));

    if (!isAdmin) {
      throw new ForbiddenException("Không có quyền thực hiện thao tác này");
    }

    ChiTietMuonTra chiTiet = chiTietMuonTraRepository.findById(chiTietId)
        .orElseThrow(() -> new NotFoundException("Không tìm thấy chi tiết mượn trả"));

    BanSaoSach banSaoSach = chiTiet.getBanSaoSach();
    BigDecimal giaTien = banSaoSach.getSach().getGiaTien();

    // Calculate fines and update BanSaoSach based on new status
    switch (status) {
      case Const.PHIEUMUON_CT_BORROWED: // DANG_MUON
        chiTiet.setTienPhat(BigDecimal.ZERO);
        chiTiet.setNgayTra(null);
        banSaoSach.setTrangThaiBanSaoSach(Const.BANSACH_BORROWED);
        break;

      case Const.PHIEUMUON_CT_RETURNED: // DA_TRA
        chiTiet.setNgayTra(new Date());
        // Calculate overdue fine if applicable
        Date today = new Date();
        if (chiTiet.getHanTra().before(today)) {
          long diffMillis = today.getTime() - chiTiet.getHanTra().getTime();
          long overdueDays = diffMillis / (1000 * 60 * 60 * 24);
          if (overdueDays > 0) {
            // Fine = 10% of book price * overdue days
            BigDecimal dailyFine = giaTien.multiply(new BigDecimal("0.10"));
            chiTiet.setTienPhat(dailyFine.multiply(BigDecimal.valueOf(overdueDays)));
          } else {
            chiTiet.setTienPhat(BigDecimal.ZERO);
          }
        } else {
          chiTiet.setTienPhat(BigDecimal.ZERO);
        }
        banSaoSach.setTrangThaiBanSaoSach(Const.BANSACH_AVAILABLE);
        break;

      case Const.PHIEUMUON_CT_DAMAGED: // HU_HONG
        chiTiet.setNgayTra(new Date());
        chiTiet.setTienPhat(giaTien); // Full book price
        banSaoSach.setTrangThaiBanSaoSach(Const.BANSACH_DAMAGED);
        break;

      case Const.PHIEUMUON_CT_LOST: // MAT
        chiTiet.setNgayTra(new Date());
        chiTiet.setTienPhat(giaTien); // Full book price
        banSaoSach.setTrangThaiBanSaoSach(Const.BANSACH_LOST);
        break;

      case Const.PHIEUMUON_CT_OVERDUE: // QUA_HAN
        banSaoSach.setTrangThaiBanSaoSach(Const.BANSACH_BORROWED);
        break;

      case Const.PHIEUMUON_CT_CANCELLED: // HUY
        chiTiet.setTienPhat(BigDecimal.ZERO);
        chiTiet.setNgayTra(null);
        banSaoSach.setTrangThaiBanSaoSach(Const.BANSACH_AVAILABLE);
        break;

      default:
        break;
    }

    chiTiet.setTinhTrangKhiTra(status);
    banSaoSachRepository.save(banSaoSach);
    chiTietMuonTraRepository.save(chiTiet);

    checkAndCompletePhieuMuon(chiTiet.getPhieuMuon());
  }

  private void checkAndCompletePhieuMuon(PhieuMuon phieuMuon) {
    List<ChiTietMuonTra> allDetails = chiTietMuonTraRepository
        .findByPhieuMuonPhieuMuonId(phieuMuon.getPhieuMuonId());

    boolean allTerminal = allDetails.stream().allMatch(detail -> {
      String s = detail.getTinhTrangKhiTra();
      return Const.PHIEUMUON_CT_RETURNED.equals(s)
          || Const.PHIEUMUON_CT_DAMAGED.equals(s)
          || Const.PHIEUMUON_CT_LOST.equals(s);
    });

    if (allTerminal) {
      phieuMuon.setTrangThaiPhieuMuon(Const.PHIEUMUON_COMPLETED);
    } else {
      boolean anyOverdue = allDetails.stream()
          .anyMatch(detail -> Const.PHIEUMUON_CT_OVERDUE.equals(detail.getTinhTrangKhiTra()));
      if (anyOverdue) {
        phieuMuon.setTrangThaiPhieuMuon(Const.PHIEUMUON_OVERDUE);
      } else {
        boolean allCancelled = allDetails.stream()
            .allMatch(detail -> Const.PHIEUMUON_CT_CANCELLED.equals(detail.getTinhTrangKhiTra()));
        if (allCancelled) {
          phieuMuon.setTrangThaiPhieuMuon(Const.PHIEUMUON_CANCELLED);
        } else {
          boolean anyBorrowed = allDetails.stream()
              .anyMatch(detail -> Const.PHIEUMUON_CT_BORROWED.equals(detail.getTinhTrangKhiTra()));
          if (anyBorrowed) {
            phieuMuon.setTrangThaiPhieuMuon(Const.PHIEUMUON_BORROWED);
          }
        }
      }
    }
  }

  @Override
  public Page<PhieuMuonResponse> searchPhieuMuonAdmin(String keyword, String trangThai,
      Pageable pageable) {
    Page<PhieuMuon> phieuMuonPage = phieuMuonRepository.searchPhieuMuonAdmin(
        keyword, trangThai, pageable);
    return phieuMuonPage.map(phieuMuonMapper::toResponseMapper);
  }

  @Override
  public long countPhieuMuonDangMuon() {
    return phieuMuonRepository.countByTrangThaiPhieuMuon(Const.PHIEUMUON_CT_BORROWED);
  }

  @Override
  public long countPhieuMuonQuaHan() {
    return phieuMuonRepository.countByTrangThaiPhieuMuon(Const.PHIEUMUON_CT_OVERDUE);
  }

  @Override
  public DocGiaFromChiTietResponse getDocGiaByChiTietMuonTraId(Long chiTietId) {
    return chiTietMuonTraRepository.findDocGiaByChiTietMuonTraId(chiTietId)
        .map(docGiaMapper::toDocGiaFromChiTietResponse)
        .orElseThrow(() -> new RuntimeException(
            "Không tìm thấy độc giả với chi tiết mượn trả ID: " + chiTietId));
  }
}