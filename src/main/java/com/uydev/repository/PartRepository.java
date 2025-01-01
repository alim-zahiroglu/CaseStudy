package com.uydev.repository;

import com.uydev.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
    List<Part> findAllByIsDeleted(boolean isDeleted);

    Optional<Object> findByNameAndModelIdAndIsDeleted(String name, Long modelId, boolean isDeleted);

    Part findByIdAndIsDeleted(Long partId, boolean isDeleted);

    @Query("SELECT p FROM Part p " +
            "WHERE p.name = :name " +
            "AND p.model.id = :modelId " +
            "AND p.id != :partId " +
            "AND p.isDeleted = :isDeleted")
    Part findByNameAndModelIdAndIdNotAndIsDeleted(String name, Long modelId, Long partId, boolean isDeleted);
}
