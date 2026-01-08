package org.example.apiquanlythuvien.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class AccountRequest {

  // Account info
  @NotBlank(message = "Username không được để trống")
  private String username;

  @NotBlank(message = "Password không được để trống")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
      message = "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt")
  private String password;

  // DocGia info
  @NotBlank(message = "Tên độc giả không được để trống")
  private String tenDocGia;

  @NotBlank(message = "Email không được để trống")
  @Email(message = "Email không hợp lệ")
  private String email;

  @NotBlank(message = "Số điện thoại không được để trống")
  private String soDienThoai;

  @NotBlank(message = "Địa chỉ không được để trống")
  private String diaChi;

  @NotNull(message = "Ngày sinh không được để trống")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date ngaySinh;

  // TheThuVien info
  @NotNull(message = "Ngày hết hạn không được để trống")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date ngayHetHan;

  @Min(value = 0, message = "Số lượng sách được mượn phải >= 0")
  private int soLuongSachDuocMuon;

}
