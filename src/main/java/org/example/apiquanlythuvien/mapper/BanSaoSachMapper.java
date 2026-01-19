package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.BanSaoSach;
import org.example.apiquanlythuvien.data.entity.Sach;
import org.example.apiquanlythuvien.data.request.CreateBanSaoSachRequest;
import org.example.apiquanlythuvien.data.request.UpdateBanSaoSachRequest;
import org.example.apiquanlythuvien.data.response.BanSaoSachResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BanSaoSachMapper {

  BanSaoSachResponse toBanSaoSachResponseMapper(BanSaoSach banSaoSach);

  BanSaoSach toBanSaoSachMapper(CreateBanSaoSachRequest createBanSaoSachRequest, Sach sach);

  void updateBanSaoSachFromRequest(UpdateBanSaoSachRequest request, @MappingTarget BanSaoSach banSaoSach);
}
