package com.example.demo.entity;


import jdk.jfr.DataAmount;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "manufacturer")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;
}
