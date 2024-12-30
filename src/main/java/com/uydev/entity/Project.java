package com.uydev.entity;

import com.uydev.enums.ConfigType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project extends BaseEntity{

    private String name;
    @Enumerated(EnumType.STRING)
    private ConfigType configType;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonthlyTarget> monthlyTargets = new ArrayList<>();


}
