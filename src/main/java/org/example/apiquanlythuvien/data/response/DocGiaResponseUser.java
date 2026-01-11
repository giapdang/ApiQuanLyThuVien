package org.example.apiquanlythuvien.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocGiaResponseUser {

  private Long docGiaId;
  private String tenDocGia;
  private String email;
  private String soDienThoai;
  private String diaChi;
  private Date ngaySinh;
  private BigDecimal tienKyQuy;
  private String trangThaiDocGia;
  private Long theThuVienId;

  @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
  private Date ngayHetHan;
  private String trangThai;

  @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
  private Date ngayCap;

}
