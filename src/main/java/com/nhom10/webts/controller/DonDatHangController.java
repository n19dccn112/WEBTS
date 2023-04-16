package com.nhom10.webts.controller;

import com.nhom10.webts.model.dto.DonDatHangDTO;
import com.nhom10.webts.service.DonDatHangService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin("*")
@RestController
@RequestMapping("api/DonDatHang")
@Tag(name = "DonDatHang")
public class DonDatHangController implements IBaseController<DonDatHangDTO, Long, DonDatHangService> {
 @Resource
 @Getter
 private DonDatHangService service;
}
