package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.DocGia;
import org.example.apiquanlythuvien.data.request.UpdateDocGiaAdminRequest;
import org.example.apiquanlythuvien.data.response.DocGiaFromChiTietResponse;
import org.example.apiquanlythuvien.data.response.DocGiaResponseAdmin;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DocGiaMapper {

  DocGiaResponseAdmin toDocGiaResponse(DocGia docGia);

  DocGiaResponseAdmin toDocGiaResponseAdmin(DocGia docGia);

  void updateDocGiaRequestMapper(UpdateDocGiaAdminRequest updateDocGiaAdminRequest, @MappingTarget DocGia docGia);

  DocGiaFromChiTietResponse toDocGiaFromChiTietResponse(DocGia docGia);
}
