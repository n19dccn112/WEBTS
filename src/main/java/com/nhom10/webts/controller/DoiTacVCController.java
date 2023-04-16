package com.nhom10.webts.controller;

import com.nhom10.webts.model.dto.DoiTacVCDTO;
import com.nhom10.webts.service.DoiTacVCService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin("*")
@RestController
@RequestMapping("api/DoiTacVanChuyen")
@Tag(name = "DoiTacVanChuyen")
public class DoiTacVCController implements IBaseController<DoiTacVCDTO, Long, DoiTacVCService> {
    @Resource
    @Getter
    private DoiTacVCService service;
}
