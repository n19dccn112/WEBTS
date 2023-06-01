package com.nhom10.webts.service;

import com.nhom10.webts.exception.NotFoundException;
import com.nhom10.webts.model.dto.DoiTacVCDTO;
import com.nhom10.webts.model.entity.DoiTacVC;
import com.nhom10.webts.model.entity.DonDatHang;
import com.nhom10.webts.repository.DoiTacVCRepository;
import com.nhom10.webts.repository.DonDatHangRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoiTacVCService implements IBaseService<DoiTacVCDTO, Long>, IModelMapper<DoiTacVCDTO, DoiTacVC> {
    private final DoiTacVCRepository doiTacVCRepository;
    private final DonDatHangRepository donDatHangRepository;
    private final ModelMapper modelMapper;

    public DoiTacVCService(DoiTacVCRepository doiTacVCRepository, DonDatHangRepository donDatHangRepository, ModelMapper modelMapper) {
        this.doiTacVCRepository = doiTacVCRepository;
        this.donDatHangRepository = donDatHangRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DoiTacVCDTO> findAll() {
        return createFromEntities(doiTacVCRepository.findAll());
    }

    @Override
    public DoiTacVCDTO findById(Long id) {
        return createFromE(doiTacVCRepository.getById(id));
    }

    @Override
    @Transactional
    public DoiTacVCDTO update(Long id, DoiTacVCDTO doiTacVCDTO) {
        Optional<DoiTacVC> doiTacVC = Optional.of(doiTacVCRepository.getById(id));
        doiTacVC.orElseThrow(() -> new NotFoundException(DoiTacVCDTO.class, id));
        return createFromE(doiTacVCRepository.save(updateEntity(doiTacVC.get(), doiTacVCDTO)));
    }

    @Override
    public DoiTacVCDTO save(DoiTacVCDTO doiTacVCDTO) {
        return createFromE(doiTacVCRepository.save(createFromD(doiTacVCDTO)));
    }

    @Override
    @Transactional
    public DoiTacVCDTO delete(Long id) {
        DoiTacVC doiTacVC = doiTacVCRepository.getById(id);
        donDatHangRepository.deleteAllByDoiTacVC(id);
        doiTacVCRepository.delete(doiTacVC);
        return createFromE(doiTacVC);
    }

    @Override
    public DoiTacVC createFromD(DoiTacVCDTO dto) {
        DoiTacVC doiTacVC = modelMapper.map(dto, DoiTacVC.class);
        return doiTacVC;
    }

    @Override
    public DoiTacVCDTO createFromE(DoiTacVC entity) {
        DoiTacVCDTO doiTacVCDTO = modelMapper.map(entity, DoiTacVCDTO.class);
        return doiTacVCDTO;
    }

    @Override
    @Transactional
    public DoiTacVC updateEntity(DoiTacVC entity, DoiTacVCDTO dto) {
        if (entity != null && dto != null) {
            entity.setThoiGianGH(dto.getThoiGianGH());
            entity.setHoTen(dto.getHoTen());
        }
        return entity;
    }
}
