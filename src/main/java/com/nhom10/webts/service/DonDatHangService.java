package com.nhom10.webts.service;

import com.nhom10.webts.model.dto.DonDatHangDTO;
import com.nhom10.webts.model.entity.DonDatHang;
import com.nhom10.webts.repository.ChitietDDHRepository;
import com.nhom10.webts.repository.DoiTacVCRepository;
import com.nhom10.webts.repository.DonDatHangRepository;
import com.nhom10.webts.repository.NguoiDungRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class DonDatHangService implements IBaseService<DonDatHangDTO, Long>, IModelMapper<DonDatHangDTO, DonDatHang> {
    private final DonDatHangRepository donDatHangRepository;
    private final ChitietDDHRepository chitietDDHRepository;
    private final DoiTacVCRepository doiTacVCRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final ModelMapper modelMapper;

    public DonDatHangService(DonDatHangRepository donDatHangRepository, ChitietDDHRepository chitietDDHRepository, DoiTacVCRepository doiTacVCRepository, NguoiDungRepository nguoiDungRepository, ModelMapper modelMapper) {
        this.donDatHangRepository = donDatHangRepository;
        this.chitietDDHRepository = chitietDDHRepository;
        this.doiTacVCRepository = doiTacVCRepository;
        this.nguoiDungRepository = nguoiDungRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DonDatHangDTO> findAll() {
        return createFromEntities(donDatHangRepository.findAll());
    }

    @Override
    public DonDatHangDTO findById(Long id) {
        return createFromE(donDatHangRepository.getById(id));
    }

    @Override
    @Transactional
    public DonDatHangDTO update(Long id, DonDatHangDTO donDatHangDTO) {
        DonDatHang donDatHang = donDatHangRepository.getById(id);
        return createFromE(donDatHangRepository.save(updateEntity(donDatHang, donDatHangDTO)));
    }

    @Override
    public DonDatHangDTO save(DonDatHangDTO donDatHangDTO) {
        donDatHangDTO.setThoiGianVC(new Date());
        DonDatHang donDatHang = donDatHangRepository.save(createFromD(donDatHangDTO));
        return createFromE(donDatHang);
    }

    @Override
    @Transactional
    public DonDatHangDTO delete(Long id) {
        DonDatHang donDatHang = donDatHangRepository.getById(id);
        chitietDDHRepository.deleteAllByChiTiet_DDH(id);
        donDatHangRepository.delete(donDatHang);
        return createFromE(donDatHang);
    }

    @Override
    public DonDatHang createFromD(DonDatHangDTO dto) {
        DonDatHang donDatHang = modelMapper.map(dto, DonDatHang.class);
        donDatHang.setDoiTacVC(doiTacVCRepository.findById(dto.getDoiTacVCId()).get());
        donDatHang.setNguoiDung(nguoiDungRepository.getById(dto.getMakh()));
        return donDatHang;
    }

    @Override
    public DonDatHangDTO createFromE(DonDatHang entity) {
        DonDatHangDTO donDatHangDTO = modelMapper.map(entity, DonDatHangDTO.class);
        donDatHangDTO.setDoiTacVCId(entity.getDoiTacVC().getId());
        donDatHangDTO.setMakh(entity.getNguoiDung().getId());
        return donDatHangDTO;
    }

    @Override
    @Transactional
    public DonDatHang updateEntity(DonDatHang entity, DonDatHangDTO dto) {
        if (entity!=null && dto!=null){
            entity.setTrangThai(dto.getTrangThai());
        }
        return entity;
    }
}
