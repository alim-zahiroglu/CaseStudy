package com.uydev.repository;

import com.uydev.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {
    List<Model> findAllByProject_IsDeleted(boolean isDeleted);
}
