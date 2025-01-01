package com.uydev.repository;

import com.uydev.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
    List<Part> findAllByIsDeleted(boolean isDeleted);
}
