package com.nhom10.webts.controller;

import com.nhom10.webts.model.dto.NguoiDungDTO;
import com.nhom10.webts.service.DoiTacVCService;
import com.nhom10.webts.service.NguoiDungService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin("*")
@RestController
@RequestMapping("api/NguoiDung")
@Tag(name = "NguoiDung")
public class NguoiDungController implements IBaseController<NguoiDungDTO, Long, NguoiDungService> {
    @Resource
    @Getter
    private NguoiDungService service;
}
