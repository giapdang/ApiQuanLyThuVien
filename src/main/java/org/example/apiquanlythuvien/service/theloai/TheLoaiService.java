package org.example.apiquanlythuvien.service.theloai;

import org.example.apiquanlythuvien.data.request.CreateTheLoaiAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateTheLoaiAdminRequest;
import org.example.apiquanlythuvien.data.response.TheLoaiAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TheLoaiService {

  Page<TheLoaiAdminResponse> getAllTheLoai(Pageable pageable);

  void createTheLoai(CreateTheLoaiAdminRequest request);

  void updateTheLoai(UpdateTheLoaiAdminRequest request);

  void deleteTheLoai(Long theLoaiId);
}
