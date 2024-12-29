package com.uydev.entity;

import com.uydev.enums.ConfigType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project extends BaseEntity{

    private String name;
    private int monthlyTarget;

    @Enumerated(EnumType.STRING)
    private ConfigType configType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "configuration_id", referencedColumnName = "id")
    private Configuration configuration;
}
