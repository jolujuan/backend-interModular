package edu.alumno.ismael.apr_rest_mysql_futbol.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.alumno.ismael.apr_rest_mysql_futbol.security.entity.UsuarioDb;

public interface UsuarioRepository extends JpaRepository<UsuarioDb, Integer>{
    Optional<UsuarioDb> findByNickname(String nickname);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    
}
