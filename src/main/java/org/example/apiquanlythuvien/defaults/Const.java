package org.example.apiquanlythuvien.defaults;

import java.math.BigDecimal;

public class Const {

  // doc gia status
  public static final String DOCGIA_ACTIVE = "HOAT_DONG";
  public static final String DOCGIA_INACTIVE = "VO_HIEU_HOA";

  // the thu vien status
  public static final String THETHUVIEN_ACTIVE = "HOAT_DONG";
  public static final String THETHUVIEN_INACTIVE = "VO_HIEU_HOA";

  // ban sao sach trang thai
  public static final String BANSACH_AVAILABLE = "CON";
  public static final String BANSACH_BORROWED = "DA_MUON";
  public static final String BANSACH_DAMAGED = "HU_HONG";
  public static final String BANSACH_LOST = "MAT";

  // phieu muon status
  public static final String PHIEUMUON_PENDING = "DANG_CHO";     // Đang chờ
  public static final String PHIEUMUON_OVERDUE = "QUA_HAN";      // Quá hạn
  public static final String PHIEUMUON_COMPLETED = "HOAN_TAT";   // Hoàn tất
  public static final String PHIEUMUON_CANCELLED = "HUY";        // Hủy
  public static final String PHIEUMUON_BORROWED = "DANG_MUON";   // Đang mượn
  public static final String PHIEUMUON_RETURNED = "DA_TRA";      // (existing)
  public static final String PHIEUMUON_TAT_CA = "TAT_CA";        // Tất cả (trang thai loc)

  // chi tiet phieu muon (loan detail) status
  public static final String PHIEUMUON_CT_PENDING = "DANG_CHO";   // Đang chờ
  public static final String PHIEUMUON_CT_BORROWED = "DANG_MUON"; // Đang mượn
  public static final String PHIEUMUON_CT_RETURNED = "DA_TRA";    // Đã trả
  public static final String PHIEUMUON_CT_OVERDUE = "QUA_HAN";    // Quá hạn
  public static final String PHIEUMUON_CT_DAMAGED = "HU_HONG";   // Hư hỏng
  public static final String PHIEUMUON_CT_LOST = "MAT";

  // account roles
  public static final String ROLE_ADMIN = "ADMIN";
  public static final String ROLE_USER = "USER";

  // doc gia tien ky quy mac dinh 100 nghin dong
  public static final BigDecimal DEFAULT_TIEN_KY_QUY = new BigDecimal(100000);

  // ban sao sach tinh trang (condition)
  public static final String BANSACH_CONDITION_NEW = "MOI";
  public static final String BANSACH_CONDITION_OLD = "CU";

}
