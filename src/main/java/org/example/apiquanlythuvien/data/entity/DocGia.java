package org.example.apiquanlythuvien.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "doc_gia")
public class DocGia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "doc_gia_id", nullable = false)
  private Long docGiaId;

  @Column(name = "ten_doc_gia", nullable = false)
  private String tenDocGia;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "so_dien_thoai", nullable = false, unique = true)
  private String soDienThoai;

  @Column(name = "dia_chi", nullable = false)
  private String diaChi;

  @Column(name = "ngay_sinh", nullable = false)
  private Date ngaySinh;

  @Column(name = "tien_ky_quy", nullable = false)
  private BigDecimal tienKyQuy;

  @Column(name = "trang_thai_doc_gia", nullable = false)
  private String trangThaiDocGia;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;

  @OneToOne(mappedBy = "docGia", cascade = CascadeType.ALL, orphanRemoval = true)
  private TheThuVien theThuVien;
}
