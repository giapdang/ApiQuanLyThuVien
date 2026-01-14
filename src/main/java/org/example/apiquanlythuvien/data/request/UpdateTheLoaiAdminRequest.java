package org.example.apiquanlythuvien.data.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTheLoaiAdminRequest {
    @NotNull(message = "ID thể loại không được để trống")
    private Long theLoaiId;
    @NotNull(message = "Tên thể loại không được để trống")
    private String tenTheLoai;

}
