package www.intermodular.com.appversion1.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import www.intermodular.com.appversion1.security.entity.UsuarioDb;

public interface UsuarioRepository extends JpaRepository<UsuarioDb, Long>{
    Optional<UsuarioDb> findByNickname(String nickname);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    
}
