package org.example.apiquanlythuvien.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "linh_vuc")
public class LinhVuc {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "linh_vuc_id", nullable = false)
  private Long linhVucId;

  @Column(name = "ten_linh_vuc", nullable = false)
  private String tenLinhVuc;

  @OneToMany(mappedBy = "linhVuc", orphanRemoval = true, cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Sach> sachLinhVuc;
}
