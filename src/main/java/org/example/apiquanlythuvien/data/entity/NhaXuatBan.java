package org.example.apiquanlythuvien.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "nha_xuat_ban")
public class NhaXuatBan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "nha_xuat_ban_id", nullable = false)
  private Long nhaXuatBanId;

  @Column(name = "ten_nha_xuat_ban", nullable = false)
  private String tenNhaXuatBan;

  @Column(name = "dia_chi", nullable = false)
  private String diaChi;

  @OneToMany(mappedBy = "nhaXuatBan", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Sach> sachNXB;
}
