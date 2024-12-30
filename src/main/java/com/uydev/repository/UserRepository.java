package com.uydev.repository;

import com.uydev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByIsDeleted(boolean b);

    boolean existsByUserNameAndIsDeleted(String userName, Boolean isDeleted);
}
