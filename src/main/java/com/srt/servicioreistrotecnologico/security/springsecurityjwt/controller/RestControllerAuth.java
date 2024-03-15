package com.srt.servicioreistrotecnologico.security.springsecurityjwt.controller;

import com.srt.servicioreistrotecnologico.security.springsecurityjwt.dtos.DtoAuthRespuesta;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.dtos.DtoLogin;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.dtos.DtoRegistro;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity.Roles;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity.Usuarios;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.repository.IRolesRepository;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.repository.IUsuariosRepository;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.security.JwtGenerator;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
public class RestControllerAuth {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private IRolesRepository rolesRepository;
    private IUsuariosRepository usuariosRepository;
    private JwtGenerator jwtGenerator;
    @Autowired
    public RestControllerAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IRolesRepository rolesRepository, IUsuariosRepository usuariosRepository, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.usuariosRepository = usuariosRepository;
        this.jwtGenerator = jwtGenerator;
    }

    // metodo para registro de usuarios con rol USER
    @PostMapping("registro")
    public ResponseEntity<String> registrar(@RequestBody DtoRegistro dtoRegistro){
        if (usuariosRepository.existsByUsername(dtoRegistro.getUsername())){
            return new ResponseEntity<>("El usuario ya existe, intenta con otro. ", HttpStatus.BAD_REQUEST);
        }
        // Verificar si el rol "USER" ya existe en la base de datos
        Roles roles = rolesRepository.findByName("USER");
        if (roles == null) {
            // Si no existe, crear el rol "USER" y guardarlo en la base de datos
            roles = new Roles();
            roles.setName("USER");
            rolesRepository.save(roles);
        }

        // Crear el usuario y asociarlo con el rol "USER"
        Usuarios usuarios = new Usuarios();
        usuarios.setUsername(dtoRegistro.getUsername());
        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        usuarios.setRoles(Collections.singletonList(roles));
        usuariosRepository.save(usuarios);
        return new ResponseEntity<>("Registro de usuario exitoso.", HttpStatus.OK);
    }
    // metodo para registro de usuarios con rol ADMIN
    @PostMapping("registro/admin")
    public ResponseEntity<String> registrarAdmin(@RequestBody DtoRegistro dtoRegistro){
        if (usuariosRepository.existsByUsername(dtoRegistro.getUsername())){
            return new ResponseEntity<>("El ADMIN ya existe, intenta con otro. ", HttpStatus.BAD_REQUEST);
        }
        // Verifica si el rol "ADMIN" ya existe en la base de datos
        Roles roles = rolesRepository.findByName("ADMIN");
        if (roles == null) {
            // Si no existe, crear el rol "ADMIN" y guardarlo en la base de datos
            roles = new Roles();
            roles.setName("ADMIN");
            rolesRepository.save(roles);
        }

        // Crear el usuario y asociarlo con el rol "ADMIN"
        Usuarios usuarios = new Usuarios();
        usuarios.setUsername(dtoRegistro.getUsername());
        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        usuarios.setRoles(Collections.singletonList(roles));
        usuariosRepository.save(usuarios);
        return new ResponseEntity<>("Registro de ADMIN exitoso. ", HttpStatus.OK);
    }
    // metodo para loguear usuario y obtener el token
    @PostMapping("login")
    public ResponseEntity<DtoAuthRespuesta> login(@RequestBody DtoLogin dtoLogin){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoLogin.getUsername(),dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generarToken(authentication);
        return new ResponseEntity<>(new DtoAuthRespuesta(token),HttpStatus.OK);
    }
}

