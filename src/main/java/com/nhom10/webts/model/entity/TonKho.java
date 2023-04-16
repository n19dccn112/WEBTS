package com.nhom10.webts.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ton_kho")
public class TonKho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idTonKho;

    @Column(name = "ten_nguyen_lieu")
    private String tenNguyenLieu;

    @Column(name = "so_luong_ton")
    private Float soLuongTon;

    @Column(name = "don_vi")
    private String donVi;

    @Transient
    @OneToMany
    private List<NguyenLieu> nguyenLieus;
}
