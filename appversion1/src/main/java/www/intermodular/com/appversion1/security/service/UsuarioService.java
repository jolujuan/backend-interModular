package edu.alumno.ismael.apr_rest_mysql_futbol.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.alumno.ismael.apr_rest_mysql_futbol.security.entity.UsuarioDb;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.repository.UsuarioRepository;
import jakarta.validation.constraints.NotNull;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<UsuarioDb> getByNickname(String nickname){
        return usuarioRepository.findByNickname(nickname);
    }

    public boolean existsByNickname(String nickname){
        return usuarioRepository.existsByNickname(nickname);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(@NotNull UsuarioDb usuario){
        usuarioRepository.save(usuario);
    }
}
