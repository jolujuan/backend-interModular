package www.intermodular.com.appversion1.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import www.intermodular.com.appversion1.security.entity.RolDb;

import java.util.Optional;

import www.intermodular.com.appversion1.security.entity.enums.RolNombre;


public interface RolRepository extends JpaRepository<RolDb, Integer>{
    Optional<RolDb> findByNombre(RolNombre rolNombre);   
}
