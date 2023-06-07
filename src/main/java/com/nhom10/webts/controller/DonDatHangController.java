package com.nhom10.webts.controller;

import com.nhom10.webts.model.dto.DonDatHangDTO;
import com.nhom10.webts.model.dto.OrderStatusClass;
import com.nhom10.webts.service.DonDatHangService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/DonDatHang")
@Tag(name = "DonDatHang")
public class DonDatHangController implements IBaseController<DonDatHangDTO, Long, DonDatHangService> {
   @Resource
   @Getter
   private DonDatHangService service;

   @GetMapping("/status")
   public List<OrderStatusClass> getOrderStatus() {
    return getService().findOrderStatus();
   }
}
