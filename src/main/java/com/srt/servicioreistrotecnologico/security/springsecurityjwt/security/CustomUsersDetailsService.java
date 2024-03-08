package com.srt.servicioreistrotecnologico.security.springsecurityjwt.security;

import com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity.Roles;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.entity.Usuarios;
import com.srt.servicioreistrotecnologico.security.springsecurityjwt.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUsersDetailsService implements UserDetailsService {

    private IUsuariosRepository usuariosRepository;
    @Autowired
    public CustomUsersDetailsService(IUsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }
    // metodo para traer la lista de autorizaciones por medio de una lista de roles
    public Collection<GrantedAuthority> mapToAuthorities(List<Roles> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    // metodo para obtener el usuario con todos los datos por medio de su username.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuarios = usuariosRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(usuarios.getUsername(), usuarios.getPassword(), mapToAuthorities(usuarios.getRoles()));
    }
}
