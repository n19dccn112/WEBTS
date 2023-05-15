package com.nhom10.webts.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DoiTacVCDTO {
    private Long id;
    private Date thoiGianGH;
    private String hoTen;
}
