package www.intermodular.com.appversion1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import www.intermodular.com.appversion1.model.db.CasillaDb;

@Repository
public interface CasillaRepository extends JpaRepository<CasillaDb, Long> {
   

}
