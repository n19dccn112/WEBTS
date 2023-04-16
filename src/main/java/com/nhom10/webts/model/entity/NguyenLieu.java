package com.nhom10.webts.model.entity;

import com.nhom10.webts.model.NguyenLieuId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nguyen_lieu")
public class NguyenLieu {
    @EmbeddedId
    private NguyenLieuId id;

    @Column(name = "so_luong")
    private Float soLuong;

    @Column(name = "don_vi")
    private String donVi;
}
