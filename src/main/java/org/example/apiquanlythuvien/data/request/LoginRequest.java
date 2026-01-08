package org.example.apiquanlythuvien.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

  @NotBlank(message = "không được để trống username")
  private String username;

  @NotBlank(message = "không được để trống password")
  private String password;
}
