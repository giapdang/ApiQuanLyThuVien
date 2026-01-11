package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.DocGia;
import org.example.apiquanlythuvien.data.response.DocGiaResponseAdmin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocGiaMapper {

  DocGiaResponseAdmin toDocGiaResponse(DocGia docGia);

}
