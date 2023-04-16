package com.nhom10.webts.model.entity;

import com.nhom10.webts.model.ChiTietDDHId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chi_tiet_don_dat_hang")
public class ChiTietDDH {
    @EmbeddedId
    private ChiTietDDHId id;

    @Column(name = "so_luong")
    private Long soLuong;
}
