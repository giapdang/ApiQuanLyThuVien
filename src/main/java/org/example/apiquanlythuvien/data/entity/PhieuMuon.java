package org.example.apiquanlythuvien.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "phieu_muon")
public class PhieuMuon {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "phieu_muon_id", nullable = false)
  private Long phieuMuonId;

  @Column(name = "ngay_muon", nullable = false)
  private Date ngayMuon;

  @Column(name = "trang_thai_phieu_muon", nullable = false)
  private String trangThaiPhieuMuon;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonBackReference
  @JoinColumn(name = "the_thu_vien_id", nullable = false)
  private TheThuVien theThuVien;

  @OneToMany(mappedBy = "phieuMuon", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JsonManagedReference
  private List<ChiTietMuonTra> chiTietMuonTra;
}
