package com.example.health_spring.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private  String title;

    @Column
    private  String writer;

    @Column
    private  String content;

    @Column
    private  String subtitle;

    @Column
    private  String thumbnail;

    @Column
    private Date regdate;
}
