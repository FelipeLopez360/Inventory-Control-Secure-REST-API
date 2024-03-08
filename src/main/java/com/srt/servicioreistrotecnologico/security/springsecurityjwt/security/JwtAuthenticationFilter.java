package com.srt.servicioreistrotecnologico.security.springsecurityjwt.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// la funcion de esta clase es validar la información del token y si es exitoso esta sera la autenticación en la solicitud en postman.
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUsersDetailsService customUsersDetailsService;

    @Autowired
    private JwtGenerator jwtGenerator;

    private String obtenerTokenDeSolicitud(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = obtenerTokenDeSolicitud(request);
        if (StringUtils.hasText(token) && jwtGenerator.validarToken(token)){
            String username = jwtGenerator.obtenerUsernameDeJWT(token);
            UserDetails userDetails = customUsersDetailsService.loadUserByUsername(username);
            List<String>userRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            if (userRoles.contains("USER") || userRoles.contains("ADMIN")){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}
