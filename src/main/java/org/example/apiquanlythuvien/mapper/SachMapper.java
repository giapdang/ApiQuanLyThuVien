package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.Sach;
import org.example.apiquanlythuvien.data.response.SachResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {TacGiaMapper.class})
public interface SachMapper {

  @Mapping(target = "tacGiaList", source = "sachTacGia", qualifiedByName = "fromSachTacGiaList")
  SachResponse toResponse(Sach sach);
}
