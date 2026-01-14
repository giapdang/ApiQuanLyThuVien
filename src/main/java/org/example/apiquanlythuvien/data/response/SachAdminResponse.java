package org.example.apiquanlythuvien.data.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class SachAdminResponse {

    private Long sachId;
    private String tenSach;
    private int soTrang;
    private String khoSach;
    private String anhBia;
    private BigDecimal giaTien;
    private Date namXuatBan;
    private String nhaXuatBan;
    private String linhVuc;
    private String theLoai;
    private List<TacGiaResponse> tacGiaList;
}
