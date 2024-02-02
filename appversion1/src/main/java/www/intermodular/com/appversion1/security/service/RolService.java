package edu.alumno.ismael.apr_rest_mysql_futbol.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.alumno.ismael.apr_rest_mysql_futbol.security.entity.RolDb;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.entity.enums.RolNombre;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.repository.RolRepository;
import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<RolDb> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByNombre(rolNombre);
    }

    public void save(@NotNull RolDb rol) {
        rolRepository.save(rol);
    }
}
