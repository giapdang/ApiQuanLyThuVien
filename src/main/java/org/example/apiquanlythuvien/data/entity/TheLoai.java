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
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "the_loai")
public class TheLoai {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "the_loai_id",nullable = false)
  private Long theLoaiId;

  @Column(name = "ten_the_loai",nullable = false)
  private String tenTheLoai;

  @JsonManagedReference
  @OneToMany(mappedBy = "theLoai", orphanRemoval = true,cascade = CascadeType.ALL)
  private List<Sach> sachTheLoai;
}
