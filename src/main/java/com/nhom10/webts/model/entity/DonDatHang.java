package com.nhom10.webts.model.entity;

import com.nhom10.webts.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "don_dat_hang")
public class DonDatHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "trang_thai")
    @Enumerated(EnumType.STRING)
    private OrderStatus trangThai;

    @Column(name = "thoi_gian_vc")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date thoiGianVC;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "sdt")
    private String sdt;

    @Transient
    @OneToMany
    private List<ChiTietDDH> chiTietDDHS;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "makh")
    private NguoiDung nguoiDung;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_doi_tac_vc")
    private DoiTacVC doiTacVC;
}
