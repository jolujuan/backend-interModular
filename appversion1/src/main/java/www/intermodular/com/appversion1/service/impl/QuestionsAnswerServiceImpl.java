package www.intermodular.com.appversion1.service.impl;

import java.util.List;

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
   
   
        private final QuestionAnswerRepository questionAnswerRepository ;
    
       
       
    
        public QuestionsAnswerServiceImpl(QuestionAnswerRepository questionAnswerRepository) {
            this.questionAnswerRepository = questionAnswerRepository;
        }




        @Override
        public List<QuestionAnswerList> findAllQuestionsAnswerCategoria(String resultsCategory, Sort sort) {
            List<QuestionAnswerDB> questionsCategoria = questionAnswerRepository.findByresultsCategoryContaining(resultsCategory, sort);
            // return questionAnswerRepository.findByResultsCategoryContaining(resultsCategory, sort);
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
    }
    
