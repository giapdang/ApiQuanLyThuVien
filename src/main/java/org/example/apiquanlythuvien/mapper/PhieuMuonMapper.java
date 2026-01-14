package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.PhieuMuon;
import org.example.apiquanlythuvien.data.response.PhieuMuonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhieuMuonMapper {

  @Mapping(target = "theThuVien", source = "theThuVien.theThuVienId")
  PhieuMuonResponse toResponseMapper(PhieuMuon phieuMuon);
}
