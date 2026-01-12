package org.example.apiquanlythuvien.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.util.Date;
import lombok.Data;

@Data
public class CreateTacGiaAdminRequest {

  @NotBlank(message = "Tên tác giả không được để trống")
  private String tenTacGia;

  @NotBlank(message = "Nơi làm việc không được để trống")
  private String noiLamViec;

  @NotNull(message = "Ngày tháng năm sinh không được để trống")
  @Past(message = "Ngày tháng năm sinh phải là ngày trong quá khứ")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date ngayThangNamSinh;


  @NotBlank(message = "Địa chỉ không được để trống")
  private String diaChi;
}
