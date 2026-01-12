package org.example.apiquanlythuvien.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTheLoaiAdminRequest {

  @NotBlank(message = "Tên thể loại không được để trống")
  private String tenTheLoai;
}
