package com.sistema.blog.service;

import com.sistema.blog.dto.RegistroDto;
import com.sistema.blog.entity.Usuario;

import java.util.Optional;

public interface UserService {

    public String save(RegistroDto registroDto);

    public Optional<Usuario> findByUsernameOrEmail(String username, String email);
    public Optional <Usuario>findByUsername(String username);

    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
}
