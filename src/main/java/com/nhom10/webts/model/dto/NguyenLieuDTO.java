package com.nhom10.webts.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;

@Getter
@Setter
public class NguyenLieuDTO {
    private Long tonKhoId;
    private Long monId;
    @DecimalMin("0")
    private Float soLuong;
    private String donVi;
}
