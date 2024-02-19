package www.intermodular.com.appversion1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import www.intermodular.com.appversion1.model.db.UsuariosEstadisticasDb;

@Repository
public interface UsuariosEstadisticasRepository extends JpaRepository<UsuariosEstadisticasDb, Long> {

    UsuariosEstadisticasDb findByIdUsuario(Long idUsuario);
     
  
    
}