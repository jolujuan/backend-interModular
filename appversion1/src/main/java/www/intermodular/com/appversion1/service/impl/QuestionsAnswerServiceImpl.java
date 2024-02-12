package www.intermodular.com.appversion1.service.impl;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import www.intermodular.com.appversion1.model.db.QuestionAnswerDB;
import www.intermodular.com.appversion1.model.dto.PaginaDto;
import www.intermodular.com.appversion1.model.dto.QuestionAnswerList;
import www.intermodular.com.appversion1.repository.QuestionAnswerRepository;

import www.intermodular.com.appversion1.service.QuestionsAnswerService;
import www.intermodular.com.appversion1.service.mapper.QuestionsAnswerMapper;

@Service
public class QuestionsAnswerServiceImpl implements QuestionsAnswerService {

    private final QuestionAnswerRepository questionAnswerRepository;

    public QuestionsAnswerServiceImpl(QuestionAnswerRepository questionAnswerRepository) {
        this.questionAnswerRepository = questionAnswerRepository;
    }

    @Override
    public List<QuestionAnswerList> findAllQuestionsAnswerCategoria(String resultsCategory, Sort sort) {
        List<QuestionAnswerDB> questionsCategoria = questionAnswerRepository
                .findByresultsCategoryContaining(resultsCategory, sort);
        // return
        // questionAnswerRepository.findByResultsCategoryContaining(resultsCategory,
        // sort);
        return QuestionsAnswerMapper.INSTANCE.questionsToQuestionAnswerList(questionsCategoria);
    }

   

    @Override
    public PaginaDto<QuestionAnswerList> findAllPageQuestionAnswerList(Pageable pageable) {
        Page<QuestionAnswerDB> pageQuestionsDb = questionAnswerRepository.findAll(pageable);

        return new PaginaDto<QuestionAnswerList>(
                pageQuestionsDb.getNumber(),
                pageQuestionsDb.getSize(),
                pageQuestionsDb.getTotalElements(),
                pageQuestionsDb.getTotalPages(),
                QuestionsAnswerMapper.INSTANCE.questionsToQuestionAnswerList(pageQuestionsDb.getContent()),
                pageQuestionsDb.getSort());
    }

    @Override
    public List<QuestionAnswerList> getRandomQuestionByCategory(String category) {
        return QuestionsAnswerMapper.INSTANCE.questionsToQuestionAnswerList(questionAnswerRepository.findRandomQuestionByCategory(category));
    }
    public boolean isAnswerCorrect(Long idPregunta, String resultsCorrectAnswer) {
        Optional<QuestionAnswerDB> optionalQuestionAnswer = questionAnswerRepository.findById(idPregunta);
        if (optionalQuestionAnswer.isPresent()) {
            QuestionAnswerDB questionAnswer = optionalQuestionAnswer.get();
            return questionAnswer.getResultsCorrectAnswer().equals(resultsCorrectAnswer);
        } else {
            return false; // Devuelve false si la pregunta no se encuentra
        }
    }

    @Override
    public String getAnswerCorect(Long idPregunta) {
        Optional<QuestionAnswerDB> optionalQuestionAnswer = questionAnswerRepository.findById(idPregunta);
        if (optionalQuestionAnswer.isPresent()) {
        return optionalQuestionAnswer.get().getResultsCorrectAnswer();
    } else {
        return "error"; // Devuelve false si la pregunta no se encuentra
    }
    }
}
