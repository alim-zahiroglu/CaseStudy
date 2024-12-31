package com.uydev.repository;

import com.uydev.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameAndIsDeleted(String name, boolean isDeleted);

    Project findByIdAndIsDeleted(Long projectId, boolean isDeleted);

    @Query("SELECT p from Project  p WHERE p.id = :projectId AND p.isDeleted = :isDeleted")
    boolean existsByIdAndIsDeleted(Long projectId, boolean isDeleted);
}
