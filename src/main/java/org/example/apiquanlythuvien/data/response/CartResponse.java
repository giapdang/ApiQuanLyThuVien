package org.example.apiquanlythuvien.data.response;

import java.util.List;
import lombok.Data;

@Data
public class CartResponse {

  private Long sachId;
  private String tenSach;
  private String anhBia;
  private Long banSaoSachId;
  private String tinhTrangBanSaoSach;
  private List<TacGiaResponse> tacGiaList;
}
