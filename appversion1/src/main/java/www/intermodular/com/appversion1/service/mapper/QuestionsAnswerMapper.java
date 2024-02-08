package www.intermodular.com.appversion1.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import www.intermodular.com.appversion1.model.db.QuestionAnswerDB;
import www.intermodular.com.appversion1.model.dto.QuestionAnswerList;
import www.intermodular.com.appversion1.model.dto.QuestionsAnswerInfo;

@Mapper
public interface QuestionsAnswerMapper {
    QuestionsAnswerMapper INSTANCE=Mappers.getMapper(QuestionsAnswerMapper.class);

    QuestionsAnswerInfo QuestionAnswerDBToQuestionsAnswerInfo(QuestionAnswerDB question);
    QuestionAnswerList QuestionAnswerDBToQuestionAnswerList(QuestionAnswerDB question);
    List<QuestionAnswerList> questionsToQuestionAnswerList(List<QuestionAnswerDB> questions);
}
