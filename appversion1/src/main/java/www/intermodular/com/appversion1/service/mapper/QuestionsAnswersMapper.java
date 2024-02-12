package www.intermodular.com.appversion1.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import www.intermodular.com.appversion1.model.db.QuestionsAnswersDb;
import www.intermodular.com.appversion1.model.dto.QuestionsAnswersInfo;
import www.intermodular.com.appversion1.model.dto.QuestionsAnswersList;


@Mapper(uses = QuestionsAnswersMapper.class)
public interface QuestionsAnswersMapper {
    QuestionsAnswersMapper INSTANCE= Mappers.getMapper(QuestionsAnswersMapper.class);
    List<QuestionsAnswersList> tematicasDbToTematicasList(List<QuestionsAnswersDb> tematicaDb);
    QuestionsAnswersInfo preguntaDbToPreguntaInfo(QuestionsAnswersDb preguntaDb);
}
