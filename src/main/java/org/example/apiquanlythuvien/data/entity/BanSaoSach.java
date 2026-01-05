package org.example.apiquanlythuvien.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ban_sao_sach")
public class BanSaoSach {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ban_sao_sach_id", nullable = false)
  private Long banSaoSachId;

  @Column(name = "tinh_trang_ban_sao_sach", nullable = false)
  private String tinhTrangBanSaoSach;

  @Column(name = "trang_thai_ban_sao_sach", nullable = false)
  private String trangThaiBanSaoSach;

  @OneToMany(mappedBy = "banSaoSach", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<ChiTietMuonTra> chiTietMuonTra;

  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  @JsonManagedReference
  @JoinColumn(name = "sach_id", nullable = false)
  private Sach sach;


}
