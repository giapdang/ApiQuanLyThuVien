package org.example.apiquanlythuvien.data.response;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocGiaFromChiTietResponse {
  private Long docGiaId;
  private String tenDocGia;
  private String email;
  private String soDienThoai;
  private String diaChi;
  private String trangThaiDocGia;
  private Date ngaySinh;
  private BigDecimal tienKyQuy;
}
