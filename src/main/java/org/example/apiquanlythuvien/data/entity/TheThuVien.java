package org.example.apiquanlythuvien.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "the_thu_vien")
public class TheThuVien {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "the_thu_vien_id", nullable = false)
  private Long theThuVienId;

  @Column(name = "ngay_het_han", nullable = false)
  private Date ngayHetHan;

  @Column(name = "trang_thai", nullable = false)
  private String trangThai;

  @Column(name = "so_luong_sach_duoc_muon", nullable = false)
  private int soLuongSachDuocMuon;

  @Column(name = "ngay_cap", nullable = false)
  private Date ngayCap;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "doc_gia_id", nullable = false)
  private DocGia docGia;

  @OneToMany(mappedBy = "theThuVien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<PhieuMuon> phieuMuon;
}
