package com.sistema.blog.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "publicaciones",uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo",nullable = false)
    private String title;

    @Column(name = "descripcion",nullable = false)
    private String description;
    //contenido
    @Column(name = "contenido",nullable = false)
    private String content;

    @Column(name = "fecha_creacion")
    private LocalDateTime date;

    //hace referencia el mappeby a como llamamo al campo que havce relacion en la otra entidad

    @JsonBackReference
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Comment> comments =new HashSet<>();

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Post() {
    }

    public Post(Long id, String title, String description, String content, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.date = date;
    }
}
