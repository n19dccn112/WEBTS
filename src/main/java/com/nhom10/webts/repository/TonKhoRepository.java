package com.nhom10.webts.repository;

import com.nhom10.webts.model.entity.TonKho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TonKhoRepository extends JpaRepository<TonKho, Long> {
}
