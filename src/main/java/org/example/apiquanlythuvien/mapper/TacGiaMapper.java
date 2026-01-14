package org.example.apiquanlythuvien.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.example.apiquanlythuvien.data.entity.SachTacGia;
import org.example.apiquanlythuvien.data.entity.TacGia;
import org.example.apiquanlythuvien.data.request.CreateTacGiaAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateTacGiaAdminRequest;
import org.example.apiquanlythuvien.data.response.TacGiaAdminResponse;
import org.example.apiquanlythuvien.data.response.TacGiaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TacGiaMapper {

  TacGiaResponse toResponse(TacGia tacGia);

  @Named("fromSachTacGiaList")
  default List<TacGiaResponse> fromSachTacGiaList(List<SachTacGia> sachTacGiaList) {
    return sachTacGiaList.stream()
        .map(this::fromSachTacGia)
        .collect(Collectors.toList());
  }

  @Mapping(target = "tacGiaId", source = "tacGia.tacGiaId")
  @Mapping(target = "tenTacGia", source = "tacGia.tenTacGia")
  TacGiaResponse fromSachTacGia(SachTacGia sachTacGia);

  TacGiaAdminResponse toTacGiaResponseAdminMapper(TacGia tacGia);

  TacGia toEntityTacGiaMapper(CreateTacGiaAdminRequest request);

  void updateTacGiaFromRequest(UpdateTacGiaAdminRequest request, @MappingTarget TacGia tacGia);
}
