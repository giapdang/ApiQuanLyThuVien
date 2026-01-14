package org.example.apiquanlythuvien.data.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhieuMuonResponse {

  private Long phieuMuonId;
  private Long theThuVien;
  private Date ngayMuon;
  private String trangThaiPhieuMuon;
}
