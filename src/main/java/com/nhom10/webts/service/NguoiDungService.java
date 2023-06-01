package com.nhom10.webts.service;

import com.nhom10.webts.exception.NotFoundException;
import com.nhom10.webts.model.JwtResponse;
import com.nhom10.webts.model.LoginRequest;
import com.nhom10.webts.model.MessageResponse;
import com.nhom10.webts.model.SignupRequest;
import com.nhom10.webts.model.dto.NguoiDungDTO;
import com.nhom10.webts.model.entity.NguoiDung;
import com.nhom10.webts.repository.DonDatHangRepository;
import com.nhom10.webts.repository.MonRepository;
import com.nhom10.webts.repository.NguoiDungRepository;
import com.nhom10.webts.security.jwt.JwtUtils;
import com.nhom10.webts.security.services.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NguoiDungService implements IBaseService<NguoiDungDTO, Long>, IModelMapper<NguoiDungDTO, NguoiDung> {
    private final NguoiDungRepository nguoiDungRepository;
    private final MonRepository monRepository;
    private final DonDatHangRepository donDatHangRepository;
    private final ModelMapper modelMapper;
    final private AuthenticationManager authenticationManager;
    final private JwtUtils jwtUtils;
    final private PasswordEncoder encoder;

    public NguoiDungService(NguoiDungRepository nguoiDungRepository, MonRepository monRepository, DonDatHangRepository donDatHangRepository, ModelMapper modelMapper, AuthenticationManager authenticationManager, JwtUtils jwtUtils, PasswordEncoder encoder) {
        this.nguoiDungRepository = nguoiDungRepository;
        this.monRepository = monRepository;
        this.donDatHangRepository = donDatHangRepository;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @Override
    public List<NguoiDungDTO> findAll() {
        return createFromEntities(nguoiDungRepository.findAll());
    }

    @Override
    public NguoiDungDTO findById(Long id) {
        Optional<NguoiDung> nhanVien  = nguoiDungRepository.findById(id);
        return createFromE(nhanVien.get());
    }

    @Override
    @Transactional
    public NguoiDungDTO update(Long id, NguoiDungDTO nhanVienDTO) {
        Optional<NguoiDung> nhanVien  = nguoiDungRepository.findById(id);
        return createFromE(nguoiDungRepository.save(updateEntity(nhanVien.get(), nhanVienDTO)));
    }
    @Override
    public NguoiDungDTO save(NguoiDungDTO nguoiDungDTO) {
        NguoiDung nguoiDung = createFromD(nguoiDungDTO);
        nguoiDungRepository.save(nguoiDung);
        return createFromE(nguoiDung);
    }

    @Override
    @Transactional
    public NguoiDungDTO delete(Long id) {
        Optional<NguoiDung> nhanVien  = nguoiDungRepository.findById(id);
        nguoiDungRepository.delete(nhanVien.get());
        donDatHangRepository.deleteAllByKhachHang(id);
        return createFromE(nhanVien.get());
    }

    @Override
    public NguoiDung createFromD(NguoiDungDTO dto) {
        NguoiDung nguoiDung = modelMapper.map(dto, NguoiDung.class);
        nguoiDung.setMatKhau(encoder.encode(dto.getMatKhau()));
        return nguoiDung;
    }

    @Override
    public NguoiDungDTO createFromE(NguoiDung entity) {
        NguoiDungDTO nguoiDungDTO = modelMapper.map(entity, NguoiDungDTO.class);
        nguoiDungDTO.setMatKhau("");
        return nguoiDungDTO;
    }

    @Override
    @Transactional
    public NguoiDung updateEntity(NguoiDung entity, NguoiDungDTO dto) {
        if(entity!=null && dto!=null){
            entity.setHoTen(dto.getHoTen());
            entity.setDiaChi(dto.getDiaChi());
            entity.setSdt(dto.getSdt());
            entity.setVaiTro(dto.getVaiTro());
        }
        return entity;
    }

    public ResponseEntity<?> checkLogin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // if go there, the user/password is correct
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // generate jwt to return to client
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        NguoiDung entity = nguoiDungRepository.getById(userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getSdt(),
                roles.get(0)));
    }

    public ResponseEntity<?> register(SignupRequest signUpRequest) {
        if (nguoiDungRepository.existsByHoTen(signUpRequest.getHoTen())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        // Create new user's account
        NguoiDungDTO dto = modelMapper.map(signUpRequest, NguoiDungDTO.class);
        dto.setVaiTro(signUpRequest.getVaiTro());
        save(dto);
        return new ResponseEntity<>(new MessageResponse("User registered successfully!"), HttpStatus.CREATED);
    }
}
