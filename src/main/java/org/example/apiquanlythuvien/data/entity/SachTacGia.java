package org.example.apiquanlythuvien.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sach_tac_gia")
public class SachTacGia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sach_tac_gia_id", nullable = false)
  private Long sachTacGiaId;

  @ManyToOne(optional = false)
  @JsonManagedReference
  @JoinColumn(name = "tac_gia_id", nullable = false)
  private TacGia tacGia;

  @ManyToOne(optional = false)
  @JsonManagedReference
  @JoinColumn(name = "sach_id", nullable = false)
  private Sach sach;
}
