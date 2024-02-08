package www.intermodular.com.appversion1.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import www.intermodular.com.appversion1.model.dto.PaginaDto;
import www.intermodular.com.appversion1.model.dto.QuestionAnswerList;

public interface QuestionsAnswerService {
    public PaginaDto<QuestionAnswerList> findAllPageQuestionAnswerList(Pageable pageable);
    public List<QuestionAnswerList> findAllQuestionsAnswerCategoria(String results_category,Sort sort);
}
