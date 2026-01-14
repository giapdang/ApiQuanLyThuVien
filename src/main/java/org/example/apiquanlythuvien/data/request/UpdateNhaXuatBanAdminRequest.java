package org.example.apiquanlythuvien.data.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateNhaXuatBanAdminRequest {
    @NotNull(message = "ID nhà xuất bản không được để trống")
    private Long nhaXuatBanId;
    @NotNull(message = "Tên nhà xuất bản không được để trống")
    private String tenNhaXuatBan;
    @NotNull(message = "Địa chỉ không được để trống")
    private String diaChi;
}
