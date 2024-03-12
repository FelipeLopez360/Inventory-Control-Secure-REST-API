package com.srt.servicioreistrotecnologico.security.springsecurityjwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }
    // este bean se encarga de verificar la informacion de los usuarios que se loguean en la API
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    // este bean se encarga de encriptar todas las contraseñas
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // este bean incorpora el filtro de seguridad de Json web token de la clase JwtAuthenticationFilter
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
    // este bean establece una cadena de filtros de seguridad en la API ( permisos segun rol de ususarios )
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable() //inhabilita configuraciones
                .exceptionHandling() // permite el manejo de excepciones
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //punto de entrada personalizado de autenticaciones no autorizadas
                .and()
                .sessionManagement() //gestion de sesiones
                .and()
                .authorizeHttpRequests()// toda peticion http debe ser autorizada
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET,"URL").hasAnyAuthority("ADMIN","USER")
                .requestMatchers(HttpMethod.POST,"URL").hasAnyAuthority("ADMIN", "USER") //! hasAnyAuthority para multiples roles
                .requestMatchers(HttpMethod.PUT,"URL").hasAuthority("ADMIN") //! hasAuthority para un solo rol
                .requestMatchers(HttpMethod.DELETE,"URL").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
        }
}
