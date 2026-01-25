package org.example.apiquanlythuvien.data.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ChiTietSachResponse {

  private Long sachId;
  private String tenSach;
  private String anhBia;
  private int soTrang;
  private String khoSach;
  private Date namXuatBan;
  private String nhaXuatBan;
  private String linhVuc;
  private String theLoai;
  private BigDecimal giaTien;
  private List<TacGiaResponse> tacGiaList;
}
