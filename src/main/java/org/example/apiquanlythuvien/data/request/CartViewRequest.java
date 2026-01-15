package org.example.apiquanlythuvien.data.request;

import java.util.List;
import lombok.Data;

@Data
public class CartViewRequest {

  List<Long> selectedBanSaoSachIds;

}
