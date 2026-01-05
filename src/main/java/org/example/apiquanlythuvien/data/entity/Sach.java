package org.example.apiquanlythuvien.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sach")
public class Sach {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sach_id", nullable = false)
  private Long sachId;

  @Column(name = "ten_sach", nullable = false)
  private String tenSach;

  @Column(name = "so_trang", nullable = false)
  private int soTrang;

  @Column(name = "kho_sach", nullable = false)
  private String khoSach;

  @Column(name = "anh_bia", nullable = false)
  private String anhBia;

  @Column(name = "gia_tien", nullable = false, precision = 15, scale = 2)
  private BigDecimal giaTien;

  @Column(name = "nam_xuat_ban", nullable = false)
  private Date namXuatBan;

  @OneToMany(mappedBy = "sach", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<BanSaoSach> banSaoSach;

  @ManyToOne(optional = false)
  @JsonBackReference
  @JoinColumn(name = "nha_xuat_ban_id", nullable = false)
  private NhaXuatBan nhaXuatBan;

  @ManyToOne(optional = false)
  @JsonBackReference
  @JoinColumn(name = "linh_vuc_id", nullable = false)
  private LinhVuc linhVuc;

  @ManyToOne(optional = false)
  @JsonBackReference
  @JoinColumn(name = "the_loai_id", nullable = false)
  private TheLoai theLoai;

  @OneToMany(mappedBy = "sach", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<SachTacGia> sachTacGia;
}
