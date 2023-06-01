package com.nhom10.webts.repository;

import com.nhom10.webts.model.ChiTietDDHId;
import com.nhom10.webts.model.entity.ChiTietDDH;
import com.nhom10.webts.model.entity.DonDatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChitietDDHRepository  extends JpaRepository<ChiTietDDH, ChiTietDDHId> {

    @Modifying
    @Query(value = "DELETE chi_tiet_don_dat_hang where id_don_dat_hang=?1", nativeQuery = true)
    void deleteAllByChiTiet_DDH(Long id_DonDatHang);

    @Modifying
    @Query(value = "DELETE chi_tiet_don_dat_hang where mon_id=?1", nativeQuery = true)
    void deleteAllByMon(Long id_Mon);
    ChiTietDDH findById_Mon_IdAndId_DonDatHang_Id(Long idMon, Long idDDH);
    List<ChiTietDDH> findAllById_DonDatHang_Id(Long idDDH);
}
