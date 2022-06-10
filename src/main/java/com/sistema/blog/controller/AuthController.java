package com.sistema.blog.controller;

import com.sistema.blog.Security.JWTAuthResponseDto;
import com.sistema.blog.Security.JwtTokenProvider;
import com.sistema.blog.dto.LoginDto;
import com.sistema.blog.dto.RegistroDto;
import com.sistema.blog.repository.RolRepository;
import com.sistema.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("login")
    public ResponseEntity<JWTAuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication =authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //obetenemos el token del jwt token provider
        String token =jwtTokenProvider.generarToken(authentication);
        return  ResponseEntity.ok(new JWTAuthResponseDto(token));

    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUser(@RequestBody RegistroDto registroDto){
        if(userService.existsByUsername(registroDto.getUsername())){
            return new ResponseEntity<>(Collections.singletonMap("response","Username ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }

        if(userService.existsByEmail(registroDto.getEmail())){
            return new ResponseEntity<>(Collections.singletonMap("response","ese email ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }

        userService.save(registroDto);
        return new ResponseEntity<>(Collections.singletonMap("response","Usuario registrado exitosamente"),HttpStatus.OK);

    }
}
