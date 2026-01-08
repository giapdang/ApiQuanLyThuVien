package org.example.apiquanlythuvien.mapper;

import org.example.apiquanlythuvien.data.entity.Account;
import org.example.apiquanlythuvien.data.entity.DocGia;
import org.example.apiquanlythuvien.data.entity.TheThuVien;
import org.example.apiquanlythuvien.data.request.AccountQuanTriRequest;
import org.example.apiquanlythuvien.data.request.AccountRequest;
import org.example.apiquanlythuvien.defaults.Const;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

  @Mapping(source = "username", target = "username")
  @Mapping(source = "password", target = "password")
  @Mapping(target = "role", constant = Const.ROLE_USER)
  Account toAccountEntity(AccountRequest request);

  @Mapping(source = "tenDocGia", target = "tenDocGia")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "soDienThoai", target = "soDienThoai")
  @Mapping(source = "diaChi", target = "diaChi")
  @Mapping(source = "ngaySinh", target = "ngaySinh")
  @Mapping(target = "tienKyQuy", expression = "java(org.example.apiquanlythuvien.defaults.Const.DEFAULT_TIEN_KY_QUY)")
  @Mapping(target = "trangThaiDocGia", constant = Const.DOCGIA_ACTIVE)
  DocGia toDocGiaEntity(AccountRequest request);

  @Mapping(source = "ngayHetHan", target = "ngayHetHan")
  @Mapping(target = "trangThai", constant = Const.THETHUVIEN_ACTIVE)
  @Mapping(source = "soLuongSachDuocMuon", target = "soLuongSachDuocMuon")
  @Mapping(target = "ngayCap", expression = "java(new java.util.Date())")
  TheThuVien toTheThuVienEntity(AccountRequest request);

  @Mapping(source = "username", target = "username")
  @Mapping(source = "password", target = "password")
  @Mapping(target = "role", constant = Const.ROLE_ADMIN)
  Account toAccountQuanTriEntity(AccountQuanTriRequest request);
}

