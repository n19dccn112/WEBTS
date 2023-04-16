package com.nhom10.webts.model.entity;

import com.nhom10.webts.model.VaiTro;
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
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "vai_tro")
    @Enumerated(EnumType.STRING)
    private VaiTro vaiTro;

    @Column(name = "mat_khau")
    private String matKhau;

    @Transient
    @OneToMany
    private List<Mon> mons;
}
