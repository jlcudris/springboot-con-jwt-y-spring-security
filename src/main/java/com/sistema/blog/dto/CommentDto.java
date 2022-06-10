package com.sistema.blog.dto;

import com.sistema.blog.entity.Post;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentDto {

    private long id;
    @NotEmpty(message = "name no deb ser vacio o nulo")
    @Size(min =2,max = 255,message = "el nombre del cometario debe tener como minimo 2 caracteres y maximo 255")
    private String name;
    @Email
    @NotEmpty(message = "el email no debe ser vacio u nulo")
    private String email;
    @NotEmpty(message = "el cuerpo no puede estar vacio")
    @Size(min = 10,max = 254,message = "el cuerpo del comentario de tener minimo de 10 caracteres y maximo 254")
    private String body;
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public CommentDto() {
    }

    public CommentDto(long id, String name, String email, String body, Post post) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
        this.post=post;

    }

}
