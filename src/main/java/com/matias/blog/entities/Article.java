package com.matias.blog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "article", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "content", nullable = false)
    private String content;

    @JsonBackReference
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Comment> comments = new HashSet<>();
}
