package org.example.apiquanlythuvien.data.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRecommendationResponse {

  private Long sachId;
  private String tenSach;
  private List<TacGiaResponse> tacGiaList;
  private String anhBia;
  private Double score;
}
