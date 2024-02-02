package edu.alumno.ismael.apr_rest_mysql_futbol.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.alumno.ismael.apr_rest_mysql_futbol.security.entity.RolDb;

import java.util.Optional;

import edu.alumno.ismael.apr_rest_mysql_futbol.security.entity.enums.RolNombre;


public interface RolRepository extends JpaRepository<RolDb, Integer>{
    Optional<RolDb> findByNombre(RolNombre rolNombre);   
}
