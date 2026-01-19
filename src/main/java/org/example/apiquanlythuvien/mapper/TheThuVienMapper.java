package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.TheThuVien;
import org.example.apiquanlythuvien.data.request.UpdateTheThuVienRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TheThuVienMapper {

  @Mapping(target = "theThuVienId", ignore = true)
  void updateTheThuVienMapper(UpdateTheThuVienRequest request,@MappingTarget TheThuVien theThuVien);
}
