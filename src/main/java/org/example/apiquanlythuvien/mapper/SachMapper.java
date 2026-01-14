package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.Sach;
import org.example.apiquanlythuvien.data.response.ChiTietSachResponse;
import org.example.apiquanlythuvien.data.response.SachResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {TacGiaMapper.class})
public interface SachMapper {

  @Mapping(target = "tacGiaList", source = "sachTacGia", qualifiedByName = "fromSachTacGiaList")
  SachResponse toResponse(Sach sach);

  @Mapping(target = "tacGiaList", source = "sachTacGia", qualifiedByName = "fromSachTacGiaList")
  @Mapping(target = "theLoai", source = "theLoai.tenTheLoai")
  @Mapping(target = "nhaXuatBan", source = "nhaXuatBan.tenNhaXuatBan")
  @Mapping(target = "linhVuc", source = "linhVuc.tenLinhVuc")
  ChiTietSachResponse toChiTietResponse(Sach sach);
}
