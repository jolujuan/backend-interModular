package www.intermodular.com.appversion1.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import www.intermodular.com.appversion1.model.db.DadoMovimientoDb;



@Repository
public interface DadoMovimientoRepository extends JpaRepository<DadoMovimientoDb, Long> {

}
