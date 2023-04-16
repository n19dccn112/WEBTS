package com.nhom10.webts.repository;

import com.nhom10.webts.model.entity.DoiTacVC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoiTacVCRepository extends JpaRepository<DoiTacVC, Long> {
}
