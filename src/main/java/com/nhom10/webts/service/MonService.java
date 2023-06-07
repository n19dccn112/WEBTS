package com.nhom10.webts.service;

import com.nhom10.webts.model.dto.MonDTO;
import com.nhom10.webts.model.entity.Mon;
import com.nhom10.webts.repository.ChitietDDHRepository;
import com.nhom10.webts.repository.MonRepository;
import com.nhom10.webts.repository.NguyenLieuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MonService implements IBaseService<MonDTO, Long>, IModelMapper<MonDTO, Mon>{
    private final MonRepository monRepository;
    private final ChitietDDHRepository chitietDDHRepository;
    private final NguyenLieuRepository nguyenLieuRepository;
    private final ModelMapper modelMapper;

    public MonService(MonRepository monRepository, ChitietDDHRepository chitietDDHRepository, NguyenLieuRepository nguyenLieuRepository, ModelMapper modelMapper) {
        this.monRepository = monRepository;
        this.chitietDDHRepository = chitietDDHRepository;
        this.nguyenLieuRepository = nguyenLieuRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MonDTO> findAll() {
        return createFromEntities(monRepository.findAll());
    }

    @Override
    public MonDTO findById(Long id) {
        return createFromE(monRepository.findById(id).get());
    }

    @Override
    public MonDTO update(Long id, MonDTO monDTO) {
        Mon mon = monRepository.getById(id);
        return createFromE(monRepository.save(updateEntity(mon, monDTO)));
    }

    @Override
    public MonDTO save(MonDTO monDTO) {
        return createFromE(monRepository.save(createFromD(monDTO)));
    }

    @Override
    @Transactional
    public MonDTO delete(Long id) {
        Mon mon = monRepository.getById(id);
        MonDTO monDTO = createFromE(mon);
        try {
            chitietDDHRepository.deleteAllByMon(id);
            nguyenLieuRepository.deleteAllByMon(id);
        }
        catch (Exception  e){
        }
        monRepository.delete(mon);
        return monDTO;
    }

    @Override
    public Mon createFromD(MonDTO dto) {
        Mon mon = modelMapper.map(dto, Mon.class);
        return mon;
    }

    @Override
    public MonDTO createFromE(Mon entity) {
        MonDTO monDTO = modelMapper.map(entity, MonDTO.class);
        return monDTO;
    }

    @Override
    @Transactional
    public Mon updateEntity(Mon entity, MonDTO dto) {
        if (entity!=null && dto!=null){
            entity.setTenMon(dto.getTenMon());
//            System.out.println(dto.getTenMon());
            entity.setHinhAnh(dto.getHinhAnh());
//            System.out.println(dto.getHinhAnh());
            entity.setGia(dto.getGia());
//            System.out.println(dto.getGia());
        }
        return entity;
    }
}
