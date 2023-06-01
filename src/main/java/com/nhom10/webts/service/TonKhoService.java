package com.nhom10.webts.service;

import com.nhom10.webts.model.dto.TonKhoDTO;
import com.nhom10.webts.model.entity.TonKho;
import com.nhom10.webts.repository.NguyenLieuRepository;
import com.nhom10.webts.repository.TonKhoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TonKhoService implements IBaseService<TonKhoDTO, Long>, IModelMapper<TonKhoDTO, TonKho> {
    private final TonKhoRepository tonKhoRepository;
    private final NguyenLieuRepository nguyenLieuRepository;
    private final ModelMapper modelMapper;

    public TonKhoService(TonKhoRepository tonKhoRepository, NguyenLieuRepository nguyenLieuRepository, ModelMapper modelMapper) {
        this.tonKhoRepository = tonKhoRepository;
        this.nguyenLieuRepository = nguyenLieuRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TonKhoDTO> findAll() {
        return createFromEntities(tonKhoRepository.findAll());
    }

    @Override
    public TonKhoDTO findById(Long id) {
        return createFromE(tonKhoRepository.getById(id));
    }

    @Override
    @Transactional
    public TonKhoDTO update(Long id, TonKhoDTO tonKhoDTO) {
        TonKho tonKho = tonKhoRepository.getById(id);
        return createFromE(tonKhoRepository.save(updateEntity(tonKho, tonKhoDTO)));
    }

    @Override
    public TonKhoDTO save(TonKhoDTO tonKhoDTO) {
        return createFromE(tonKhoRepository.save(createFromD(tonKhoDTO)));
    }

    @Override
    @Transactional
    public TonKhoDTO delete(Long id) {
        TonKho tonKho = tonKhoRepository.getById(id);
        try {
            nguyenLieuRepository.deleteAllByTonKho(id);
        }
        catch (Exception e){

        }
        tonKhoRepository.delete(tonKho);
        return createFromE(tonKho);
    }

    @Override
    public TonKho createFromD(TonKhoDTO dto) {
        return modelMapper.map(dto, TonKho.class);
    }

    @Override
    public TonKhoDTO createFromE(TonKho entity) {
        return modelMapper.map(entity, TonKhoDTO.class);
    }

    @Override
    @Transactional
    public TonKho updateEntity(TonKho entity, TonKhoDTO dto) {
        if(entity!=null && dto!=null){
            entity.setTenNguyenLieu(dto.getTenNguyenLieu());
            entity.setSoLuongTon(dto.getSoLuongTon());
            entity.setDonVi(dto.getDonVi());
        }
        return entity;
    }
}
