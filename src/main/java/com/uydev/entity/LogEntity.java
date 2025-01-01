package com.uydev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "logs")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean operationStatus;
    private String action;
    private String entity;
    private Long entityId;
    private LocalDateTime timestamp;

}