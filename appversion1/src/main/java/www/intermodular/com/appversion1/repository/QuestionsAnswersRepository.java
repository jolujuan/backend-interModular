package www.intermodular.com.appversion1.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import www.intermodular.com.appversion1.model.db.QuestionsAnswersDb;

@Repository
public interface QuestionsAnswersRepository extends JpaRepository<QuestionsAnswersDb, Long> {
    @Query(value = "SELECT * FROM questions_answers ORDER BY RAND() LIMIT 1", nativeQuery = true)
    QuestionsAnswersDb findRandomQuestion();

    @Query(value = "SELECT * FROM questions_answers WHERE results_category = :category ORDER BY RAND() LIMIT 1", nativeQuery = true)
    QuestionsAnswersDb findRandomQuestionByCategory(@Param("category") String category);

    @Query(value = "SELECT resultsCategory FROM questions_answers ORDER BY RAND() LIMIT 1", nativeQuery = true)
    List<QuestionsAnswersDb> findAll();
}
