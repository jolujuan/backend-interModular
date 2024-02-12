package www.intermodular.com.appversion1.model.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "questions_answers")
public class QuestionAnswerDB {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPregunta;
    
    private String resultsType;
    private String resultsDifficulty;
    private String resultsCategory;
    private String resultsQuestion;
    private String resultsCorrectAnswer;
    private String resultsIncorrectAnswers;
}
