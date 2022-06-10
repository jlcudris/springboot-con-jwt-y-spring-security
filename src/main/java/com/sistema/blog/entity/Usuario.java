package com.sistema.blog.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios",uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String username;
    private String email;
    private String password;
    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "rol_id",referencedColumnName = "id") )
    private Set <Rol> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Usuario(Long id, String nombre, String username, String email, String password, LocalDateTime date, Set<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.date = date;
        this.roles = roles;
    }

    public Usuario() {
    }
}
