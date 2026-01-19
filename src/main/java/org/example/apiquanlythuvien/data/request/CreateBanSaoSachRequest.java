package org.example.apiquanlythuvien.data.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBanSaoSachRequest {

  private Long sachId;

  @NotNull(message = "Tình trạng bản sao sách không được để trống")
  private String tinhTrangBanSaoSach;

  @NotNull(message = "Trạng thái bản sao sách không được để trống")
  private String trangThaiBanSaoSach;

}
