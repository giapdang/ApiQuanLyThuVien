package org.example.apiquanlythuvien.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateNhaXuatBanAdminRequest {

  @NotBlank(message = "Tên nhà xuất bản không được để trống")
  private String tenNhaXuatBan;

  @NotBlank(message = "Địa chỉ không được để trống")
  private String diaChi;
}
