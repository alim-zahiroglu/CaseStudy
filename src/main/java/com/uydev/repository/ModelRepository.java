package com.uydev.repository;

import com.uydev.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {

    List<Model> findAllByIsDeleted(boolean isDeleted);

    @Query("SELECT m FROM Model m " +
            "JOIN m.project p " +
            "WHERE p.id = :projectId " +
            "AND p.isDeleted = :isDeleted " +
            "AND m.isDeleted = :modelIsDeleted")
    List<Model> findAllByProjectIdAndIsDeleted(Long projectId, boolean isDeleted, boolean modelIsDeleted);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END " +
            "FROM Model m " +
            "WHERE m.name = :modelName " +
            "AND m.project.id = :projectId " +
            "AND m.isDeleted = :isDeleted")
    boolean existsByNameAndProjectIdAndIsDeleted(String modelName, Long projectId, boolean isDeleted);


    @Query("SELECT m FROM Model m " +
            "JOIN m.project p " +
            "WHERE p.id = :projectId " +
            "AND m.isDeleted = :isDeleted")
    List<Model> findAllByProjectIdAndModelIsDeleted(Long projectId, boolean isDeleted);

    @Query("SELECT CASE WHEN count (m)>0 THEN  true ELSE false END " +
            "from Model m WHERE m.id = :modelId " +
            "AND m.isDeleted = :isDeleted")
    boolean existsByIdAndIsDeleted(Long modelId, boolean isDeleted);

    @Query("SELECT CASE WHEN count (m)>0 THEN  true ELSE false END " +
            "from Model m WHERE m.name = :modelName " +
            "AND m.project.id = :projectId " +
            "AND m.id != :modelId " +
            "AND m.isDeleted = :isDeleted")
    boolean existsByNameAndProjectIdAndIdNotAndIsDeleted(String modelName, Long projectId, Long modelId, boolean isDeleted);
}
