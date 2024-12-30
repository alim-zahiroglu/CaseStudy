package com.uydev.repository;

import com.uydev.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameAndIsDeleted(String name, boolean isDeleted);
}
