package com.nhom10.webts.repository;

import com.nhom10.webts.model.entity.DonDatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonDatHangRepository extends JpaRepository<DonDatHang, Long> {

    @Modifying
    @Query(value = "DELETE don_dat_hang where id_doi_tac_vc=?1", nativeQuery = true)
    void deleteAllByDoiTacVC(Long id_DoiTacVC);

    @Modifying
    @Query(value = "DELETE don_dat_hang where makh=?1", nativeQuery = true)
    void deleteAllByKhachHang(Long MaKH);
}
