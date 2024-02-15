package www.intermodular.com.appversion1.service;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import www.intermodular.com.appversion1.model.dto.PaginaDto;
import www.intermodular.com.appversion1.model.dto.QuestionAnswerList;

public interface QuestionsAnswerService {
    public String getAnswerCorect(Long idPregunta);
    public PaginaDto<QuestionAnswerList> findAllPageQuestionAnswerList(Pageable pageable);
    public List<QuestionAnswerList> findAllQuestionsAnswerCategoria(String results_category,Sort sort);
    public List<QuestionAnswerList> getRandomQuestionByCategory(String category, Long idTable);
    public boolean isAnswerCorrect(Long idPregunta,String resultsCorrectAnswer);
}
