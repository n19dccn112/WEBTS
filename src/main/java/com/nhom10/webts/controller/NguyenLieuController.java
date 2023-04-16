package com.nhom10.webts.controller;

import com.nhom10.webts.model.NguyenLieuId;
import com.nhom10.webts.model.dto.NguoiDungDTO;
import com.nhom10.webts.model.dto.NguyenLieuDTO;
import com.nhom10.webts.service.DoiTacVCService;
import com.nhom10.webts.service.NguyenLieuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/NguyenLieu")
@Tag(name = "NguyenLieu")
public class NguyenLieuController{
    @Resource
    @Getter
    private NguyenLieuService service;

    @GetMapping("")
    public List<NguyenLieuDTO> getAll(@RequestParam(required = false) Long idMon) {
        if (idMon == null)
            return getService().findAll();
        else
            return getService().findAll(idMon);
    }

    @GetMapping("/{mon_id}-{id_tonkho}")
    public NguyenLieuDTO get1(@PathVariable Long mon_id, @PathVariable Long id_tonkho) {
        return service.findById(mon_id, id_tonkho);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{mon_id}-{id_tonkho}")
    public NguyenLieuDTO update(@PathVariable Long mon_id, @PathVariable Long id_tonkho, @Valid @RequestBody NguyenLieuDTO dto) {
        dto.setMonId(mon_id);
        dto.setTonKhoId(id_tonkho);
        return service.update(mon_id, id_tonkho, dto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{mon_id}-{id_tonkho}")
    public NguyenLieuDTO delete(@PathVariable Long mon_id, @PathVariable Long id_tonkho) {
        return service.delete(mon_id, id_tonkho);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public NguyenLieuDTO insert(@Valid @RequestBody NguyenLieuDTO d) {
        return getService().save(d);
    }
}
