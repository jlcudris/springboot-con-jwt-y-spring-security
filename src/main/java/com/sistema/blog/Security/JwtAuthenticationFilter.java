package com.sistema.blog.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //obtenemos el token de la solicitud http
        String token =obetenerJwtSolicitud(request);
        if(StringUtils.hasText(token) && jwtTokenProvider.validarToken(token)){
            //obetener el username token
            String username = jwtTokenProvider.obtenerUsernameJWT(token);
            //cargamos el usuario asociado al token
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            //establecemos la seguridad
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);

    }

    //Bearer token de acceso
    private String obetenerJwtSolicitud(HttpServletRequest request){
        String bearertoken =request.getHeader("Authorization");
        if(StringUtils.hasText(bearertoken) && bearertoken.startsWith("Bearer")){
            return bearertoken.substring(7,bearertoken.length());
        }
        return  null;
    }
}
