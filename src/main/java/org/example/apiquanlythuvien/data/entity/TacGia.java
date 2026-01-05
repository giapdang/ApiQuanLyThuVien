package org.example.apiquanlythuvien.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "tac_gia")
public class TacGia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "tac_gia_id", nullable = false)
  private Long tacGiaId;

  @Column(name = "ten_tac_gia", nullable = false)
  private String tenTacGia;

  @Column(name = "noi_lam_viec")
  private String noiLamViec;

  @Column(name = "ngay_thang_nam_sinh")
  private Date ngayThangNamSinh;

  @Column(name = "dia_chi")
  private String diaChi;

  @OneToMany(mappedBy = "tacGia", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<SachTacGia> sachTacGia;
}
