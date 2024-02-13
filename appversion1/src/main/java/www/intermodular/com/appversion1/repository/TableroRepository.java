package www.intermodular.com.appversion1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import www.intermodular.com.appversion1.model.db.TableroDb;




@Repository
public interface TableroRepository extends JpaRepository<TableroDb, Long> {
     
    TableroDb findByIdTablero(Long idTablero);
    Page<TableroDb> findAll(Pageable pageable);
    
}
