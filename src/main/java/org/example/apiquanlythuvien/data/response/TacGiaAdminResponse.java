package org.example.apiquanlythuvien.data.response;

import java.util.Date;
import lombok.Data;

@Data
public class TacGiaAdminResponse {

  private Long tacGiaId;
  private String tenTacGia;
  private String noiLamViec;
  private Date ngayThangNamSinh;
  private String diaChi;
}
