package org.example.apiquanlythuvien.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanSaoSachResponse {

  private Long banSaoSachId;
  private String tinhTrangBanSaoSach;
  private String trangThaiBanSaoSach;
}
