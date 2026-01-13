package org.example.apiquanlythuvien.data.request;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class UpdateDocGiaAdminRequest {

  private Long docGiaId;
  private String tenDocGia;
  private String email;
  private String soDienThoai;
  private String diaChi;
  private Date ngaySinh;
  private BigDecimal tienKyQuy;
  private String trangThaiDocGia;
}
