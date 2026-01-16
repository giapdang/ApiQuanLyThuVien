package org.example.apiquanlythuvien.data.response;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietMuonTraResponse {

    private Long chiTietMuonTraId;
    private Date ngayTra;
    private Date hanTra;
    private String tinhTrangKhiTra;
    private BigDecimal tienPhat;
    private Long banSaoSachId;
    private String tenSach;
    private String anhBia;
}
