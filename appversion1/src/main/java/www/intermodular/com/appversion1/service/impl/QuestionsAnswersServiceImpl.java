package www.intermodular.com.appversion1.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.intermodular.com.appversion1.model.dto.QuestionsAnswersInfo;
import www.intermodular.com.appversion1.model.dto.QuestionsAnswersList;
import www.intermodular.com.appversion1.repository.QuestionsAnswersRepository;
import www.intermodular.com.appversion1.service.QuestionsAnswersService;
import www.intermodular.com.appversion1.service.mapper.QuestionsAnswersMapper;


@Service
public class QuestionsAnswersServiceImpl implements QuestionsAnswersService{
    private final QuestionsAnswersRepository questionsAnswersRepository;

    @Autowired
    public QuestionsAnswersServiceImpl(QuestionsAnswersRepository questionsAnswersRepository) {
        this.questionsAnswersRepository = questionsAnswersRepository;
    }
    @Override
    public QuestionsAnswersInfo obtenerTematica() {
        List<QuestionsAnswersList> listaTematicas = QuestionsAnswersMapper.INSTANCE.tematicasDbToTematicasList(questionsAnswersRepository.findAll());

        // Verificar si la lista de temáticas no está vacía
        if (!listaTematicas.isEmpty()) {
            // Obtener un índice aleatorio dentro del rango de la lista de temas
            int indiceAleatorio = new Random().nextInt(listaTematicas.size());
            
            // Obtener la temática aleatoria usando el índice aleatorio
            QuestionsAnswersList tematicaAleatoria = listaTematicas.get(indiceAleatorio);
            
            // Crear y devolver un objeto QuestionsAnswersInfo con la temática aleatoria
            QuestionsAnswersInfo tematicaInfo = new QuestionsAnswersInfo();
            tematicaInfo.setResults_category_questions(tematicaAleatoria.getResults_category());
            // Añade aquí más campos si es necesario
            
            return tematicaInfo;
        } else {
            // Devuelve null o maneja el caso de lista vacía según tus requerimientos
            return null;
        }
    }
    
}
