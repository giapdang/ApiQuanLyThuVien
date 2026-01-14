package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.NhaXuatBan;
import org.example.apiquanlythuvien.data.request.CreateNhaXuatBanAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateNhaXuatBanAdminRequest;
import org.example.apiquanlythuvien.data.response.NhaXuatBanAdminResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NhaXuatBanMapper {

  NhaXuatBanAdminResponse toNhaXuatBanAdminResponseMapper(NhaXuatBan nhaXuatBan);

  NhaXuatBan toNhaXuatBanEntityMapper(CreateNhaXuatBanAdminRequest createNhaXuatBan);

  void updateNhaXuatBanFromRequest(UpdateNhaXuatBanAdminRequest request, @MappingTarget NhaXuatBan nhaXuatBan);
}
