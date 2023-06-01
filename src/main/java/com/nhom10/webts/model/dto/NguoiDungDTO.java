package com.nhom10.webts.model.dto;

import com.nhom10.webts.model.VaiTro;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NguoiDungDTO {
    private Long id;
    private String hoTen;
    private String diaChi;

    @NotNull
    @NotBlank
    private String sdt;
    private VaiTro vaiTro;
    private String matKhau;
}
