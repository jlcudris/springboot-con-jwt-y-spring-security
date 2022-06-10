package com.sistema.blog.service;

import com.sistema.blog.dto.RegistroDto;
import com.sistema.blog.entity.Rol;
import com.sistema.blog.entity.Usuario;
import com.sistema.blog.repository.RolRepository;
import com.sistema.blog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
     private RolRepository rolRepository;


    @Override
    public String save(RegistroDto registroDto) {
        Usuario user =mapearToUsuario(registroDto);
        user.setDate(LocalDateTime.now());
        Rol roles = rolRepository.findByNombre("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        usuarioRepository.save(user);
        return "Usuario Registrado";

    }

    @Override
    public Optional<Usuario> findByUsernameOrEmail(String username, String email) {
        return usuarioRepository.findByUsernameOrEmail(username,email);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
      return   usuarioRepository.existsByUsername(username);

    }

    @Override
    public Boolean existsByEmail(String email) {
        return   usuarioRepository.existsByEmail(email);
    }

    private Usuario mapearToUsuario(RegistroDto registroDto){
        Usuario user = new Usuario();
        user.setNombre(registroDto.getNombre());
        user.setEmail(registroDto.getEmail());
        user.setUsername(registroDto.getUsername());
        user.setPassword(passwordEncoder.encode(registroDto.getPassword()));
        return user;

    }
}
