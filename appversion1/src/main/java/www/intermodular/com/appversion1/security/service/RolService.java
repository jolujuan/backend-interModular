package www.intermodular.com.appversion1.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import www.intermodular.com.appversion1.security.entity.RolDb;
import www.intermodular.com.appversion1.security.entity.enums.RolNombre;
import www.intermodular.com.appversion1.security.repository.RolRepository;
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
