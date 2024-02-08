package www.intermodular.com.appversion1.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import www.intermodular.com.appversion1.model.db.QuestionAnswerDB;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswerDB, Long> {
    //metodos que nos interese crear 
    
    List<QuestionAnswerDB> findByresultsCategoryContaining(String resultsCategory,Sort sort);
    Page<QuestionAnswerDB> findAll(Pageable pageable);
}
