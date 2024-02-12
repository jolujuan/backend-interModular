package www.intermodular.com.appversion1.model.dto;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionsAnswersList implements Serializable{
    private Long idPregunta;
    private String results_type;
    private String results_difficulty;
    private String results_category;
    private String results_question;
    private String results_correct_answer;
    private List<String> results_incorrect_answers;
}
