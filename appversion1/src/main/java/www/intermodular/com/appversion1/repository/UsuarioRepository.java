package www.intermodular.com.appversion1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import www.intermodular.com.appversion1.model.dto.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Puedes agregar métodos personalizados según tus necesidades
}
