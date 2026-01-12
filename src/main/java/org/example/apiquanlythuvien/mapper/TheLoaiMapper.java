package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.TheLoai;
import org.example.apiquanlythuvien.data.request.CreateTheLoaiAdminRequest;
import org.example.apiquanlythuvien.data.response.TheLoaiAdminResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheLoaiMapper {

  TheLoaiAdminResponse toTheLoaiAdminResponseMapper(TheLoai theLoai);

  TheLoai toTheLoaiMapper(CreateTheLoaiAdminRequest request);
}
