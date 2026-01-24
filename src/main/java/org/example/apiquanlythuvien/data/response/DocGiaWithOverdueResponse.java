package org.example.apiquanlythuvien.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocGiaWithOverdueResponse {
    private DocGiaResponseAdmin docGia;
    private long overdueCount;
}
