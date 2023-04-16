package com.nhom10.webts.service;

import com.nhom10.webts.exception.NotFoundException;
import com.nhom10.webts.model.ChiTietDDHId;
import com.nhom10.webts.model.NguyenLieuId;
import com.nhom10.webts.model.dto.ChiTietDDHDTO;
import com.nhom10.webts.model.dto.NguyenLieuDTO;
import com.nhom10.webts.model.entity.ChiTietDDH;
import com.nhom10.webts.model.entity.DonDatHang;
import com.nhom10.webts.model.entity.Mon;
import com.nhom10.webts.model.entity.TonKho;
import com.nhom10.webts.repository.ChitietDDHRepository;
import com.nhom10.webts.repository.DonDatHangRepository;
import com.nhom10.webts.repository.MonRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ChiTietDDHService  implements IBaseService<ChiTietDDHDTO, ChiTietDDHId>, IModelMapper<ChiTietDDHDTO, ChiTietDDH>{
    private final ChitietDDHRepository chitietDDHRepository;
    private final ModelMapper modelMapper;
    private final MonRepository monRepository;
    private final DonDatHangRepository donDatHangRepository;

    public ChiTietDDHService(ChitietDDHRepository chitietDDHRepository, ModelMapper modelMapper, MonRepository monRepository, DonDatHangRepository donDatHangRepository) {
        this.chitietDDHRepository = chitietDDHRepository;
        this.modelMapper = modelMapper;
        this.monRepository = monRepository;
        this.donDatHangRepository = donDatHangRepository;
    }

    @Override
    public List<ChiTietDDHDTO> findAll() {
        return createFromEntities(chitietDDHRepository.findAll());
    }

    public List<ChiTietDDHDTO> findAll(Long idDDH) {
        return createFromEntities(chitietDDHRepository.findAllById_DonDatHang_Id(idDDH));
    }

    @Override
    public ChiTietDDHDTO findById(ChiTietDDHId chiTietDDHId) {
        ChiTietDDH entity = chitietDDHRepository.findById(chiTietDDHId)
                .orElseThrow(() -> new NotFoundException(ChiTietDDHDTO.class, chiTietDDHId.getDonDatHang().getId()));
        return createFromE(entity);
    }

    public ChiTietDDHDTO findById(Long idDDH, Long idMon) {
        ChiTietDDH entity = chitietDDHRepository.findById_Mon_IdAndId_DonDatHang_Id(idMon, idDDH);
        return createFromE(entity);
    }

    @Override
    @Transactional
    public ChiTietDDHDTO update(ChiTietDDHId chiTietDDHId, ChiTietDDHDTO chiTietDDHDTO) {
        ChiTietDDH entity = chitietDDHRepository.findById(chiTietDDHId)
                .orElseThrow(() -> new NotFoundException(ChiTietDDHDTO.class, chiTietDDHId.getDonDatHang().getId()));
        return createFromE(chitietDDHRepository.save(updateEntity(entity, chiTietDDHDTO)));
    }

    @Transactional
    public ChiTietDDHDTO update(Long idDDH, Long idMon, ChiTietDDHDTO chiTietDDHDTO) {
        ChiTietDDH entity = chitietDDHRepository.findById_Mon_IdAndId_DonDatHang_Id(idMon, idDDH);
        return createFromE(chitietDDHRepository.save(updateEntity(entity, chiTietDDHDTO)));
    }

    @Override
    public ChiTietDDHDTO save(ChiTietDDHDTO chiTietDDHDTO) {
        return createFromE(chitietDDHRepository.save(createFromD(chiTietDDHDTO)));
    }

    @Override
    @Transactional
    public ChiTietDDHDTO delete(ChiTietDDHId chiTietDDHId) {
        ChiTietDDH chiTietDDH = chitietDDHRepository.findById(chiTietDDHId)
                .orElseThrow(() -> new NotFoundException(ChiTietDDHDTO.class, chiTietDDHId.getDonDatHang().getId()));
        chitietDDHRepository.delete(chiTietDDH);
        return createFromE(chiTietDDH);
    }

    @Transactional
    public ChiTietDDHDTO delete(Long idDDH, Long idMon) {
        ChiTietDDH chiTietDDH = chitietDDHRepository.findById_Mon_IdAndId_DonDatHang_Id(idMon, idDDH);
        chitietDDHRepository.delete(chiTietDDH);
        return createFromE(chiTietDDH);
    }

    private ChiTietDDHId findId(ChiTietDDHDTO dto){
        Optional<Mon> mon = Optional.ofNullable(monRepository.findById(dto.getMonId())
                .orElseThrow(() -> new NotFoundException(Mon.class, dto.getMonId())));
        Optional<DonDatHang> donDatHang = Optional.ofNullable(donDatHangRepository.findById(dto.getDonDatHangId()))
                .orElseThrow(()->new NotFoundException(TonKho.class, dto.getDonDatHangId()));
        return new ChiTietDDHId(donDatHang.get(), mon.get());
    }

    @Override
    public ChiTietDDH createFromD(ChiTietDDHDTO dto) {
        ChiTietDDH entity = modelMapper.map(dto, ChiTietDDH.class);
        entity.setId(findId(dto));
        return entity;
    }

    @Override
    public ChiTietDDHDTO createFromE(ChiTietDDH entity) {
        ChiTietDDHDTO dto = modelMapper.map(entity, ChiTietDDHDTO.class);
        dto.setDonDatHangId(entity.getId().getDonDatHang().getId());
        dto.setMonId(entity.getId().getMon().getId());
        return dto;
    }

    @Override
    @Transactional
    public ChiTietDDH updateEntity(ChiTietDDH entity, ChiTietDDHDTO dto) {
        if (entity != null && dto != null) {
            entity.setSoLuong(dto.getSoLuong());
        }
        return entity;
    }
}
