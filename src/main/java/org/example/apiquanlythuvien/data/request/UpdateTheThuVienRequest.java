package org.example.apiquanlythuvien.data.request;

import java.util.Date;
import lombok.Data;

@Data
public class UpdateTheThuVienRequest {

  private Long theThuVienId;
  private Date ngayHetHan;
  private String trangThai;
}
