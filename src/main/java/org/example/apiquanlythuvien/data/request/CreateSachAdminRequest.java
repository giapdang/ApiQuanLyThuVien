package org.example.apiquanlythuvien.data.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class CreateSachAdminRequest {

    @NotBlank(message = "Tên sách không được để trống")
    private String tenSach;

    @Min(value = 1, message = "Số trang phải ít nhất là 1")
    private int soTrang;

    @NotBlank(message = "Khổ sách không được để trống")
    private String khoSach;

    @NotBlank(message = "Ảnh bìa không được để trống")
    private String anhBia;

    @NotNull(message = "Giá tiền không được để trống")
    @Min(value = 0, message = "Giá tiền không được âm")
    private BigDecimal giaTien;

    @NotNull(message = "Năm xuất bản không được để trống")
    private Date namXuatBan;

    @NotNull(message = "Nhà xuất bản không được để trống")
    private Long nhaXuatBanId;

    @NotNull(message = "Lĩnh vực không được để trống")
    private Long linhVucId;

    @NotNull(message = "Thể loại không được để trống")
    private Long theLoaiId;

    @NotNull(message = "Danh sách tác giả không được để trống")
    private List<Long> tacGiaIds;
}
