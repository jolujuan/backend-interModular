package www.intermodular.com.appversion1.model.dto;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionsAnswersInfo implements Serializable{
    private Long idPregunta_questions;
    private String results_type_questions;
    private String results_difficulty_questions;
    private String results_category_questions;
    private String results_question_questions;
    private String results_correct_answer_questions;
    private List<String> results_incorrect_answers_questions;
}
