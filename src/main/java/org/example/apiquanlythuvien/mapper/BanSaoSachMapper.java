package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.BanSaoSach;
import org.example.apiquanlythuvien.data.response.BanSaoSachResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BanSaoSachMapper {

  BanSaoSachResponse toBanSaoSachResponseMapper(BanSaoSach banSaoSach);
}
