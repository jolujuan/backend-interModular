package www.intermodular.com.appversion1.model.db;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "questions_answers")
public class QuestionsAnswersDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPregunta;
    private String resultsType;
    private String resultsDifficulty;
    private String resultsCategory;
    private String resultsQuestion;
    private String resultsCorrectAnswer;
    private List<String> resultsIncorrectAnswers;
}