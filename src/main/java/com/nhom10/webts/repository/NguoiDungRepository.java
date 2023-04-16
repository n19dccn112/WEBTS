package com.nhom10.webts.repository;

import com.nhom10.webts.model.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    Optional<NguoiDung> findAllByHoTen(String hoTen);

    Boolean existsByHoTen(String hoTen);

    Boolean existsBySdt(String sdt);
}
