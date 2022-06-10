package com.sistema.blog.dto;

import com.sistema.blog.entity.Comment;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class PostDto {


    private Long id;
    @NotEmpty
    @Size(min =2,max = 255,message = "el titulo de la publicacion debe tener como minimo 2 caracteres y maximo 255")
    private String title;
    @NotEmpty
    @Size(min =2,max = 255,message = "la description de la publicacion debe tener como minimo 2 caracteres y maximo 255")
    private String description;
    @NotEmpty(message = "el conyenido de la publicacion no puede estar vacio")
    @Size(min =2,max = 255,message = "el contenido de la publicacion debe tener como minimo 2 caracteres y maximo 255")
    private String content;
    private LocalDateTime date;
    private Set<Comment> comments;

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

    public PostDto() {
    }

    public PostDto(Long id, String title, String description, String content, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.date = date;
    }
}
