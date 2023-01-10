package com.example.health_spring.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="member")
public class Member {
    @Id
    private String id;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String email;
}
