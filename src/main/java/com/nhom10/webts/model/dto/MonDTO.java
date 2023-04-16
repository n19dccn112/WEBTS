package com.nhom10.webts.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MonDTO {
    private Long id;
    private String tenMon;
    private String hinhAnh;
    private Long gia;
}
