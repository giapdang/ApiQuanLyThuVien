package org.example.apiquanlythuvien.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountQuanTriRequest {

  // Account info
  @NotBlank(message = "Username không được để trống")
  private String username;

  @NotBlank(message = "Password không được để trống")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
      message = "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt")
  private String password;
}
