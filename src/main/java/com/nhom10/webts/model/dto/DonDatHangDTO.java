package com.nhom10.webts.model.dto;

import com.nhom10.webts.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class DonDatHangDTO {
    private Long id;

    private OrderStatus trangThai;
    private Date thoiGianVC;

    private Long makh;

    private Long doiTacVCId;

    private String diaChi;

    private String sdt;
}
