package com.nhom10.webts.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequest {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 20)
    private String hoTen;

    private String diaChi;

    @NotBlank
    @Size(max = 10)
    @Size(min = 10)
    private String sdt;
    private VaiTro vaiTro;

    @NotBlank
    @Size(min = 6, max = 40)
    private String matKhau;
}
