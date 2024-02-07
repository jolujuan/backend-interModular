package www.intermodular.com.appversion1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionsAnswerInfo {
    private Long idPregunta;

    private String resultsType;
    private String resultsDifficulty;
    private String resultsCategory;
    private String resultsQuestion;
    private String resultsCorrectAnswer;
    private String resultsIncorrectAnswers;


}
