package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.Sach;
import org.example.apiquanlythuvien.data.request.CreateSachAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateSachAdminRequest;
import org.example.apiquanlythuvien.data.response.ChiTietSachResponse;
import org.example.apiquanlythuvien.data.response.SachAdminResponse;
import org.example.apiquanlythuvien.data.response.SachResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = { TacGiaMapper.class })
public interface SachMapper {

  @Mapping(target = "tacGiaList", source = "sachTacGia", qualifiedByName = "fromSachTacGiaList")
  SachResponse toResponse(Sach sach);

  @Mapping(target = "tacGiaList", source = "sachTacGia", qualifiedByName = "fromSachTacGiaList")
  @Mapping(target = "theLoai", source = "theLoai.tenTheLoai")
  @Mapping(target = "nhaXuatBan", source = "nhaXuatBan.tenNhaXuatBan")
  @Mapping(target = "linhVuc", source = "linhVuc.tenLinhVuc")
  ChiTietSachResponse toChiTietResponse(Sach sach);

  @Mapping(target = "tacGiaList", source = "sachTacGia", qualifiedByName = "fromSachTacGiaList")
  @Mapping(target = "theLoai", source = "theLoai.tenTheLoai")
  @Mapping(target = "nhaXuatBan", source = "nhaXuatBan.tenNhaXuatBan")
  @Mapping(target = "linhVuc", source = "linhVuc.tenLinhVuc")
  SachAdminResponse toAdminResponse(Sach sach);

  @Mapping(target = "nhaXuatBan", ignore = true)
  @Mapping(target = "linhVuc", ignore = true)
  @Mapping(target = "theLoai", ignore = true)
  @Mapping(target = "sachTacGia", ignore = true)
  @Mapping(target = "banSaoSach", ignore = true)
  Sach toSachMapper(CreateSachAdminRequest request);

  @Mapping(target = "nhaXuatBan", ignore = true)
  @Mapping(target = "linhVuc", ignore = true)
  @Mapping(target = "theLoai", ignore = true)
  @Mapping(target = "sachTacGia", ignore = true)
  @Mapping(target = "banSaoSach", ignore = true)
  void updateSachFromRequest(UpdateSachAdminRequest request, @MappingTarget Sach sach);
}
