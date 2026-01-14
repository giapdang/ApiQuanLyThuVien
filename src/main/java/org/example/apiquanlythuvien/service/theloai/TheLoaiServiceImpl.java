package org.example.apiquanlythuvien.service.theloai;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.entity.TheLoai;
import org.example.apiquanlythuvien.data.request.CreateTheLoaiAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateTheLoaiAdminRequest;
import org.example.apiquanlythuvien.data.response.TheLoaiAdminResponse;
import org.example.apiquanlythuvien.mapper.TheLoaiMapper;
import org.example.apiquanlythuvien.responsitory.TheLoaiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TheLoaiServiceImpl implements  TheLoaiService {

  private final TheLoaiRepository theLoaiRepository;
  private final TheLoaiMapper theLoaiMapper;

  @Override
  public Page<TheLoaiAdminResponse> getAllTheLoai(Pageable pageable) {
    return theLoaiRepository.findAll(pageable)
            .map(theLoaiMapper::toTheLoaiAdminResponseMapper);
  }

  @Override
  public void createTheLoai(CreateTheLoaiAdminRequest request) {
    TheLoai theLoai = theLoaiMapper.toTheLoaiMapper(request);
    theLoaiRepository.save(theLoai);
  }

  @Override
  public void updateTheLoai(UpdateTheLoaiAdminRequest request) {
    TheLoai theLoai = theLoaiRepository.findById(request.getTheLoaiId())
            .orElseThrow(() -> new RuntimeException("Thể loại không tồn tại"));
    theLoaiMapper.updateTheLoaiFromRequest(request, theLoai);
    theLoaiRepository.save(theLoai);
  }

  @Override
  public void deleteTheLoai(Long theLoaiId) {
    TheLoai theLoai = theLoaiRepository.findById(theLoaiId)
            .orElseThrow(() -> new RuntimeException("Thể loại không tồn tại"));
    theLoaiRepository.delete(theLoai);
  }
}
