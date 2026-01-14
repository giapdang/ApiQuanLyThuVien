package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.LinhVuc;
import org.example.apiquanlythuvien.data.request.CreateLinhVucAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateLinhVucAdminRequest;
import org.example.apiquanlythuvien.data.response.LinhVucAdminResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LinhVucMapper {
    LinhVucAdminResponse toLinhVucAdminResponseMapper(LinhVuc linhVuc);
    LinhVuc toLinhVucMapper(CreateLinhVucAdminRequest request);
    void updateLinhVucFromRequest(UpdateLinhVucAdminRequest request, @MappingTarget LinhVuc linhVuc);
}
