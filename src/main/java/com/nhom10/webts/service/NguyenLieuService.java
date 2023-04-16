package com.nhom10.webts.service;

import com.nhom10.webts.exception.NotFoundException;
import com.nhom10.webts.model.NguyenLieuId;
import com.nhom10.webts.model.dto.NguyenLieuDTO;
import com.nhom10.webts.model.entity.Mon;
import com.nhom10.webts.model.entity.NguyenLieu;
import com.nhom10.webts.model.entity.TonKho;
import com.nhom10.webts.repository.MonRepository;
import com.nhom10.webts.repository.NguyenLieuRepository;
import com.nhom10.webts.repository.TonKhoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NguyenLieuService implements IBaseService<NguyenLieuDTO, NguyenLieuId>, IModelMapper<NguyenLieuDTO, NguyenLieu> {
    private final NguyenLieuRepository nguyenLieuRepository;
    private final MonRepository monRepository;
    private final TonKhoRepository tonKhoRepository;
    private final ModelMapper modelMapper;

    public NguyenLieuService(NguyenLieuRepository nguyenLieuRepository, MonRepository monRepository, TonKhoRepository tonKhoRepository, ModelMapper modelMapper) {
        this.nguyenLieuRepository = nguyenLieuRepository;
        this.monRepository = monRepository;
        this.tonKhoRepository = tonKhoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<NguyenLieuDTO> findAll() {
        return createFromEntities(nguyenLieuRepository.findAll());
    }
    public List<NguyenLieuDTO> findAll(Long idMon) {
        return createFromEntities(nguyenLieuRepository.findAllById_Mon_Id(idMon));
    }

    @Override
    public NguyenLieuDTO findById(NguyenLieuId nguyenLieuId) {
        return createFromE(nguyenLieuRepository.getById(nguyenLieuId));
    }

    @Transactional
    public NguyenLieuDTO findById(Long idMon, Long idTonKho) {
        NguyenLieu nguyenLieu = nguyenLieuRepository.findById_Mon_IdAndId_TonKho_IdTonKho(idMon, idTonKho);
        return createFromE(nguyenLieu);
    }

    @Override
    @Transactional
    public NguyenLieuDTO update(NguyenLieuId nguyenLieuId, NguyenLieuDTO nguyenLieuDTO) {
        NguyenLieu nguyenLieu = nguyenLieuRepository.getById(nguyenLieuId);
        return createFromE(nguyenLieuRepository.save(updateEntity(nguyenLieu, nguyenLieuDTO)));
    }

    @Transactional
    public NguyenLieuDTO update(Long idMon, Long idTonKho, NguyenLieuDTO nguyenLieuDTO) {
        NguyenLieu nguyenLieu = nguyenLieuRepository.findById_Mon_IdAndId_TonKho_IdTonKho(idMon, idTonKho);
        return createFromE(nguyenLieuRepository.save(updateEntity(nguyenLieu, nguyenLieuDTO)));
    }

    @Override
    public NguyenLieuDTO save(NguyenLieuDTO nguyenLieuDTO) {
        return createFromE(nguyenLieuRepository.save(createFromD(nguyenLieuDTO)));
    }

    @Override
    @Transactional
    public NguyenLieuDTO delete(NguyenLieuId nguyenLieuId) {
        NguyenLieu nguyenLieu = nguyenLieuRepository.getById(nguyenLieuId);
        nguyenLieuRepository.delete(nguyenLieu);
        return createFromE(nguyenLieu);
    }

    @Transactional
    public NguyenLieuDTO delete(Long idMon, Long idTonKho) {
        NguyenLieu nguyenLieu = nguyenLieuRepository.findById_Mon_IdAndId_TonKho_IdTonKho(idMon, idTonKho);
        nguyenLieuRepository.delete(nguyenLieu);
        return createFromE(nguyenLieu);
    }

    private NguyenLieuId findId(NguyenLieuDTO dto){
        Optional<Mon> mon = Optional.ofNullable(monRepository.findById(dto.getMonId())
                .orElseThrow(() -> new NotFoundException(Mon.class, dto.getMonId())));
        Optional<TonKho> tonKho = Optional.ofNullable(tonKhoRepository.findById(dto.getTonKhoId()))
                .orElseThrow(()->new NotFoundException(TonKho.class, dto.getTonKhoId()));
        return new NguyenLieuId(tonKho.get(), mon.get());
    }

    @Override
    public NguyenLieu createFromD(NguyenLieuDTO dto) {
        NguyenLieu nguyenLieu = modelMapper.map(dto, NguyenLieu.class);
        nguyenLieu.setId(findId(dto));
        return nguyenLieu;
    }

    @Override
    public NguyenLieuDTO createFromE(NguyenLieu entity) {
        NguyenLieuDTO nguyenLieuDTO = modelMapper.map(entity, NguyenLieuDTO.class);
        nguyenLieuDTO.setMonId(entity.getId().getMon().getId());
        nguyenLieuDTO.setTonKhoId(entity.getId().getTonKho().getIdTonKho());
        return nguyenLieuDTO;
    }

    @Override
    @Transactional
    public NguyenLieu updateEntity(NguyenLieu entity, NguyenLieuDTO dto) {
        if(entity!=null && dto!=null){
            entity.setSoLuong(dto.getSoLuong());
        }
        return entity;
    }
}
