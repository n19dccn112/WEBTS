package com.nhom10.webts.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;

@Setter
@Getter
public class ChiTietDDHDTO {
    private Long donDatHangId;
    private Long monId;
    @DecimalMin(value = "1")
    private Long soLuong;
}
