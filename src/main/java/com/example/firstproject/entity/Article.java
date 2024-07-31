package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
public class Article {
    @Id
    @GeneratedValue//자동으로 id를 생성해주는 어노테이션
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

}
