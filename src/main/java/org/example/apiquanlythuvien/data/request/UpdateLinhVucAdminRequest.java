package org.example.apiquanlythuvien.data.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateLinhVucAdminRequest {

    @NotNull(message = "ID lĩnh vực không được để trống")
    private Long linhVucId;

    @NotNull(message = "Tên lĩnh vực không được để trống")
    private String tenLinhVuc;
}
