package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.ChiTietMuonTra;
import org.example.apiquanlythuvien.data.response.ChiTietMuonTraResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChiTietMuonTraMapper {

    @Mapping(target = "banSaoSachId", source = "banSaoSach.banSaoSachId")
    @Mapping(target = "tenSach", source = "banSaoSach.sach.tenSach")
    @Mapping(target = "anhBia", source = "banSaoSach.sach.anhBia")
    ChiTietMuonTraResponse toResponseMapper(ChiTietMuonTra chiTietMuonTra);
}
