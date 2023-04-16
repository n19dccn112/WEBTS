package com.nhom10.webts.controller;

import com.nhom10.webts.model.dto.MonDTO;
import com.nhom10.webts.service.DoiTacVCService;
import com.nhom10.webts.service.MonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin("*")
@RestController
@RequestMapping("api/Mon")
@Tag(name = "Mon")
public class MonController implements IBaseController<MonDTO, Long, MonService> {
    @Resource
    @Getter
    private MonService service;
}
