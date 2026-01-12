package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.NhaXuatBan;
import org.example.apiquanlythuvien.data.request.CreateNhaXuatBanAdminRequest;
import org.example.apiquanlythuvien.data.response.NhaXuatBanAdminResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NhaXuatBanMapper {

  NhaXuatBanAdminResponse toNhaXuatBanAdminResponseMapper(NhaXuatBan nhaXuatBan);

  NhaXuatBan toNhaXuatBanEntityMapper(CreateNhaXuatBanAdminRequest createNhaXuatBan);
}
