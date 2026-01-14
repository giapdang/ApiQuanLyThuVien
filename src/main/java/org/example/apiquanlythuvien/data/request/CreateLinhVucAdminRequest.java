package org.example.apiquanlythuvien.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateLinhVucAdminRequest {

    @NotBlank(message = "Tên lĩnh vực không được để trống")
    private String tenLinhVuc;
}
