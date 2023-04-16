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
@Table(name = "mon")
public class Mon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ten_mon")
    private String tenMon;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "gia")
    private Long gia;

    @Transient
    @OneToMany
    private List<NguyenLieu> nguyenLieus;

    @Transient
    @OneToMany
    private List<ChiTietDDH> chiTietDDHS;
}
