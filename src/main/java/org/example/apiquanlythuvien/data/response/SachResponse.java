package org.example.apiquanlythuvien.data.response;

import java.util.List;
import lombok.Data;

@Data
public class SachResponse {

  private Long sachId;
  private String tenSach;
  private String anhBia;
  private List<TacGiaResponse> tacGiaList;
}
