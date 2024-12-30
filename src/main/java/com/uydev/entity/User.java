package com.uydev.entity;

import com.uydev.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(unique = true)
    private String userName;
    private String passWord;

    @Enumerated(EnumType.STRING)
    private Role role;
}
