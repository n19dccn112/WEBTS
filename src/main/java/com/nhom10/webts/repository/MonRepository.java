package com.nhom10.webts.repository;

import com.nhom10.webts.model.entity.Mon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MonRepository extends JpaRepository<Mon, Long> {
}
