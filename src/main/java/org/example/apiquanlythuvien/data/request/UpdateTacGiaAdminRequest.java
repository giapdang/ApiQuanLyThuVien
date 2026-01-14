package org.example.apiquanlythuvien.data.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateTacGiaAdminRequest {
    @NotNull(message = "ID tác giả không được để trống")
    private Long tacGiaId;
    @NotNull(message = "Tên tác giả không được để trống")
    private String tenTacGia;
    @NotNull(message = "Địa chỉ không được để trống")
    private String diaChi;
    @NotNull(message = "Nơi làm việc không được để trống")
    private String noiLamViec;
    @NotNull(message = "Ngày tháng năm sinh không được để trống")
    private Date ngayThangNamSinh;
}
