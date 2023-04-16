package com.nhom10.webts.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;

@Getter
@Setter
public class TonKhoDTO {
    private Long idTonKho;
    private String tenNguyenLieu;
    @DecimalMin("0")
    private Float soLuongTon;
    private String donVi;
}
