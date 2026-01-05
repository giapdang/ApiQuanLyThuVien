package org.example.apiquanlythuvien.defaults;

public class Const {

  // doc gia status
  public static final String DOCGIA_ACTIVE = "HOAT_DONG";
  public static final String DOCGIA_INACTIVE = "VO_HIEU_HOA";

  // the thu vien status
  public static final String THETHUVIEN_ACTIVE = "HOAT_DONG";
  public static final String THETHUVIEN_INACTIVE = "VO_HIEU_HOA";

  // ban sao sach status
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

  // chi tiet phieu muon (loan detail) status
  public static final String PHIEUMUON_CT_PENDING = "DANG_CHO";   // Đang chờ
  public static final String PHIEUMUON_CT_BORROWED = "DANG_MUON"; // Đang mượn
  public static final String PHIEUMUON_CT_RETURNED = "DA_TRA";    // Đã trả
  public static final String PHIEUMUON_CT_OVERDUE = "QUA_HAN";    // Quá hạn
  public static final String PHIEUMUON_CT_DAMAGED = "HU_HONG";   // Hư hỏng
  public static final String PHIEUMUON_CT_LOST = "MAT";

}
