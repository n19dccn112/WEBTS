package com.nhom10.webts.controller;

import com.nhom10.webts.model.ChiTietDDHId;
import com.nhom10.webts.model.dto.ChiTietDDHDTO;
import com.nhom10.webts.model.dto.DonDatHangDTO;
import com.nhom10.webts.model.dto.NguyenLieuDTO;
import com.nhom10.webts.service.ChiTietDDHService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/ChiTietDDH")
@Tag(name = "ChiTietDonDatHang")
public class ChiTietDDHController{
    @Resource
    @Getter
    private ChiTietDDHService service;

    @GetMapping("")
    public List<ChiTietDDHDTO> getAll(@RequestParam(required = false) Long donDatHangId) {
        if (donDatHangId == null)
            return getService().findAll();
        else
            return getService().findAll(donDatHangId);
    }

    @GetMapping("/{donDatHangId}-{monId}")
    public ChiTietDDHDTO get1(@PathVariable Long donDatHangId, @PathVariable Long monId) {
        return service.findById(donDatHangId, monId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{donDatHangId}-{monId}")
    public ChiTietDDHDTO update(@PathVariable Long donDatHangId, @PathVariable Long monId, @Valid @RequestBody ChiTietDDHDTO dto) {
        dto.setDonDatHangId(donDatHangId);
        dto.setMonId(monId);
        return service.update(donDatHangId, monId, dto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{donDatHangId}-{monId}")
    public ChiTietDDHDTO delete(@PathVariable Long donDatHangId, @PathVariable Long monId) {
        return service.delete(donDatHangId, monId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ChiTietDDHDTO insert(@Valid @RequestBody ChiTietDDHDTO d) {
        return getService().save(d);
    }
}