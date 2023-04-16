package com.nhom10.webts.security.services;

import com.nhom10.webts.model.entity.NguoiDung;
import com.nhom10.webts.repository.NguoiDungRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final NguoiDungRepository nguoiDungRepository;

    public UserDetailsServiceImpl(NguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String hoTen) throws UsernameNotFoundException {

        NguoiDung nguoiDung = nguoiDungRepository.findAllByHoTen(hoTen)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or email : " + hoTen)
                );
        return UserDetailsImpl.build(nguoiDung);
    }
}
