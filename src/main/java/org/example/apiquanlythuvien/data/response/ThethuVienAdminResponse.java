package org.example.apiquanlythuvien.data.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThethuVienAdminResponse {

  private Long theThuVienId;
  private Date ngayHetHan;
  private String trangThai;
  private int soLuongSachDuocMuon;
  private Date ngayCap;
  private String tenDocGia;
}
