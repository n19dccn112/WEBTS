package com.nhom10.webts.controller;

import com.nhom10.webts.model.dto.TonKhoDTO;
import com.nhom10.webts.service.DoiTacVCService;
import com.nhom10.webts.service.TonKhoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin("*")
@RestController
@RequestMapping("api/TonKho")
@Tag(name = "TonKho")
public class TonKhoController implements IBaseController<TonKhoDTO, Long, TonKhoService> {
    @Resource
    @Getter
    private TonKhoService service;
}
