package com.sistema.blog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "comentarios")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "correo")
    private String email;

    @Column(name = "cuerpo")
    private String body;
    //le decimos que solo vamos aobtener el post de os comentarios olo cuando sea necesario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publicacion_id",nullable = false)
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment() {
    }

    public Comment(Long id, String name, String email, String body, Post post) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
        this.post = post;
    }
}
