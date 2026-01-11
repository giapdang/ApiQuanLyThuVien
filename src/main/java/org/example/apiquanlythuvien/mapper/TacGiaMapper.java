package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.SachTacGia;
import org.example.apiquanlythuvien.data.entity.TacGia;
import org.example.apiquanlythuvien.data.response.TacGiaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TacGiaMapper {

  TacGiaResponse toResponse(TacGia tacGia);

  @Mapping(target = "tacGiaId", source = "tacGia.tacGiaId")
  @Mapping(target = "tenTacGia", source = "tacGia.tenTacGia")
  TacGiaResponse fromSachTacGia(SachTacGia sachTacGia);
}
