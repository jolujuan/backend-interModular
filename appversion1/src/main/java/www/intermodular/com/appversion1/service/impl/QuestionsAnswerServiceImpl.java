package www.intermodular.com.appversion1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import www.intermodular.com.appversion1.model.db.JugadorDb;
import www.intermodular.com.appversion1.model.db.QuestionAnswerDB;
import www.intermodular.com.appversion1.model.db.TableroDb;
import www.intermodular.com.appversion1.model.dto.PaginaDto;
import www.intermodular.com.appversion1.model.dto.QuestionAnswerList;
import www.intermodular.com.appversion1.repository.JugadorRepository;
import www.intermodular.com.appversion1.repository.QuestionAnswerRepository;
import www.intermodular.com.appversion1.repository.TableroRepository;
import www.intermodular.com.appversion1.service.QuestionsAnswerService;
import www.intermodular.com.appversion1.service.mapper.QuestionsAnswerMapper;

@Service
public class QuestionsAnswerServiceImpl implements QuestionsAnswerService {

    private final QuestionAnswerRepository questionAnswerRepository;
    private final TableroRepository tableroRepository;
    private final JugadorRepository jugadorRepository;
    private final TableroServiceImpl tableroServiceImpl;
    public QuestionsAnswerServiceImpl(JugadorRepository jugadorRepository,QuestionAnswerRepository questionAnswerRepository,TableroRepository tableroRepository,TableroServiceImpl tableroServiceImpl) {
        this.questionAnswerRepository = questionAnswerRepository;
        this.tableroRepository=tableroRepository;
        this.jugadorRepository =jugadorRepository;
        this.tableroServiceImpl=tableroServiceImpl;
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
    public List<QuestionAnswerList> getRandomQuestionByCategory(String category,Long idTable) {
        TableroDb questionsPartida = tableroRepository.findByIdTablero(idTable);

        if (questionsPartida == null) {
           return new ArrayList<>(); // Devuelve una lista vacía si no se encuentra el tablero
        }
    
        String preguntasHechas = questionsPartida.getPreguntasHechas();
        List<QuestionAnswerList> retorno;
    
        // Bucle para obtener una pregunta que no haya sido hecha anteriormente
        do {
            retorno = QuestionsAnswerMapper.INSTANCE.questionsToQuestionAnswerList(questionAnswerRepository.findRandomQuestionByCategory(category));
        } while (preguntasHechas.contains("" + retorno.get(0).getIdPregunta()));
    
        // Agrega el ID de la pregunta al registro de preguntas hechas
        preguntasHechas += " " + retorno.get(0).getIdPregunta();
    
        // Actualiza el campo preguntasHechas en el objeto questionsPartida en la base de datos
        questionsPartida.setPreguntasHechas(preguntasHechas);
        tableroRepository.save(questionsPartida);
    
        return retorno;
    }
    public boolean isAnswerCorrect(Long idPregunta, String resultsCorrectAnswer, Long idTable, String nickname) {
        Optional<QuestionAnswerDB> optionalQuestionAnswer = questionAnswerRepository.findById(idPregunta);
        JugadorDb idJugadorDb = jugadorRepository.findByIdUsuario(tableroServiceImpl.getIdUserJugador(nickname));
        if (optionalQuestionAnswer.isPresent()) {
            QuestionAnswerDB questionAnswer = optionalQuestionAnswer.get();
            TableroDb getTableroActual = tableroRepository.findByIdTablero(idTable);

            if (getTableroActual!=null && questionAnswer.getResultsCorrectAnswer().equals(resultsCorrectAnswer)== false) { // en caso de que sea false la respuesta me cambie el turno
                String jugadorActual;
                if (getTableroActual.getJugador1().equals(idJugadorDb)) {
                    jugadorActual="1";
                }else if (getTableroActual.getJugador2().equals(idJugadorDb)) {
                    jugadorActual="2";
                }
                else if (getTableroActual.getJugador3().equals(idJugadorDb)) {
                    jugadorActual="3";
                }
                else if (getTableroActual.getJugador4().equals(idJugadorDb)) {
                    jugadorActual="4";
                }else return false;

                switch (jugadorActual) {
                    case "1":
                            getTableroActual.setTurnoJugador(getTableroActual.getJugador2().getIdUsuario());
                            tableroRepository.save(getTableroActual);
                        break;
                        case "2":
                        if (getTableroActual.getCasillaJugador3()==null) {
                            getTableroActual.setTurnoJugador(getTableroActual.getJugador1().getIdUsuario());
                        }else
                        getTableroActual.setTurnoJugador(getTableroActual.getJugador3().getIdUsuario());
                        tableroRepository.save(getTableroActual);
                    break;
                    case "3":
                            getTableroActual.setTurnoJugador(getTableroActual.getJugador2().getIdUsuario());
                            if (getTableroActual.getCasillaJugador4()==null) {
                                getTableroActual.setTurnoJugador(getTableroActual.getJugador1().getIdUsuario());
                            }else
                            getTableroActual.setTurnoJugador(getTableroActual.getJugador4().getIdUsuario());
                            tableroRepository.save(getTableroActual);
                        break;
                        case "4":
                            getTableroActual.setTurnoJugador(getTableroActual.getJugador1().getIdUsuario());
                            tableroRepository.save(getTableroActual); 
                        break;
                    default:
                    return false;
                       
                }
            }
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
