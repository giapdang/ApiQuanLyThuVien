package org.example.apiquanlythuvien.data.request;

import lombok.Data;
import java.util.List;

@Data
public class CreatePhieuMuonRequest {
    private List<Long> selectedBanSaoSachIds;
}
