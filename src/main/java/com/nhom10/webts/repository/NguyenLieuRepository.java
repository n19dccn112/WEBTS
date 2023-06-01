package com.nhom10.webts.repository;

import com.nhom10.webts.model.NguyenLieuId;
import com.nhom10.webts.model.entity.NguyenLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NguyenLieuRepository extends JpaRepository<NguyenLieu, NguyenLieuId> {
    @Modifying
    @Query(value = "DELETE nguyen_lieu where mon_id=?1", nativeQuery = true)
    void deleteAllByMon(Long id_Mon);

    @Modifying
    @Query(value = "DELETE nguyen_lieu where ton_kho_id=?1", nativeQuery = true)
    void deleteAllByTonKho(Long id_TonKho);

    NguyenLieu findById_Mon_IdAndId_TonKho_IdTonKho(Long idMon, Long idTonKho);

    List<NguyenLieu> findAllById_Mon_Id(Long idMon);
}
