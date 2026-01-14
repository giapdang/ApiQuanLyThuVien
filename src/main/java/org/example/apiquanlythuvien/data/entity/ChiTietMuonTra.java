package org.example.apiquanlythuvien.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chi_tiet_muon_tra")
public class ChiTietMuonTra {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "chi_tiet_muon_tra_id", nullable = false)
  private Long chiTietMuonTraId;

  @Column(name = "ngay_tra", nullable = true)
  private Date ngayTra;

  @Column(name = "han_tra", nullable = false)
  private Date hanTra;

  @Column(name = "tinh_trang_khi_tra", nullable = false)
  private String tinhTrangKhiTra;

  @Column(name = "tien_phat", nullable = false)
  private BigDecimal tienPhat;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JsonBackReference
  @JoinColumn(name = "phieu_muon_id", nullable = false)
  private PhieuMuon phieuMuon;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JsonBackReference
  @JoinColumn(name = "ban_sao_sach_id", nullable = false)
  private BanSaoSach banSaoSach;
}
