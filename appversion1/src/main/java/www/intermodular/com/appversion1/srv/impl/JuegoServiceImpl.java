package www.intermodular.com.appversion1.srv.impl;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.intermodular.com.appversion1.model.dto.CasillaList;
import www.intermodular.com.appversion1.model.dto.JugadorInfo;
import www.intermodular.com.appversion1.model.dto.QuestionsAnswersInfo;
import www.intermodular.com.appversion1.model.dto.TableroInfo;
import www.intermodular.com.appversion1.repository.JugadorRepository;
import www.intermodular.com.appversion1.repository.QuestionsAnswersRepository;
import www.intermodular.com.appversion1.repository.TableroRepository;
import www.intermodular.com.appversion1.srv.DadoMovimientoService;
import www.intermodular.com.appversion1.srv.JuegoService;
import www.intermodular.com.appversion1.srv.JugadorService;
import www.intermodular.com.appversion1.srv.QuestionsAnswersService;
import www.intermodular.com.appversion1.srv.TableroService;
import www.intermodular.com.appversion1.srv.mapper.JugadorMapper;
import www.intermodular.com.appversion1.srv.mapper.QuestionsAnswersMapper;
import www.intermodular.com.appversion1.srv.mapper.TableroMapper;


@Service
public class JuegoServiceImpl implements JuegoService{
    private final TableroRepository tableroRepository;
    private final QuestionsAnswersRepository questionsAnswersRepository;
    private final JugadorRepository jugadorRepository;
    private JugadorService jugadorService;
    private TableroService tableroService;
    private QuestionsAnswersService questionsAnswersService;
    private DadoMovimientoService dadoMovimientoService;

    @Autowired
    public JuegoServiceImpl(TableroRepository tableroRepository, QuestionsAnswersRepository questionsAnswersRepository, JugadorRepository jugadorRepository) {
        this.tableroRepository = tableroRepository;
        this.questionsAnswersRepository = questionsAnswersRepository;
        this.jugadorRepository = jugadorRepository;
    }

    @Override
    public void iniciarNuevoJuego() {
        // Implementar lógica para iniciar un nuevo juego, como inicializar el tablero, etc.
        // Inicializamos el tablero (en caso de que sea necesario)
        TableroInfo tablero = TableroMapper.INSTANCE.tableroDbToTableroInfo(tableroRepository.findById(1L).orElse(null));; // Obtener el tablero desde la base de datos
        if (tablero.getCasillas_tablero() == null || tablero.getCasillas_tablero().isEmpty()) {
            tableroService.inicializarTablero(tablero);
        }

        // Actualizamos el estado del tablero para indicar que se ha iniciado un nuevo juego
        // tablero.setEstado_tablero(EstadoTablero.EN_CURSO);
        // tableroRepository.save(tablero);
    }

    @Override
    public void comenzarTurno(Long jugadorId) {
        // Obtenemos el jugador que está realizando el movimiento
        JugadorInfo jugador = JugadorMapper.INSTANCE.JugadoroDbToJugadorInfo(jugadorRepository.findById(jugadorId).orElse(null));;
        if (jugador == null) {
            // Manejo del caso en el que no se encuentra el jugador
            return;
        }

        // Lanzamos el dado de temática para determinar la temática de la pregunta
        QuestionsAnswersInfo tematicaPregunta = questionsAnswersService.obtenerTematica();

        // Obtenemos una pregunta aleatoria de la temática obtenida        
        QuestionsAnswersInfo pregunta = QuestionsAnswersMapper.INSTANCE.preguntaDbToPreguntaInfo(questionsAnswersRepository.findRandomQuestionByCategory(tematicaPregunta.getResults_category_questions()));

        // Presentamos la pregunta al jugador para que responda
        presentarCartaAlJugador(jugador, pregunta);
    }

    @Override
    public void verificarCasilla(JugadorInfo jugador) {
        // Obtenemos la casilla actual del jugador
        CasillaList casillaActual = tableroService.obtenerCasillaPorPosicion(jugador.getPosicion_jugador());
        if (casillaActual == null) {
            // Manejo del caso en el que no hay casilla actual para el jugador
            return;
        }
        
        // Implementamos la lógica correspondiente según el tipo de casilla en la que ha caído el jugador
        switch (casillaActual.getTipo()) {
            case SALIDA:
                // No hay acción especial para la casilla de salida
                break;
            case BONIFICACION:
                // Casilla de bonificación "BURRO SABELOTODO"
                // Implementamos la lógica según las reglas del juego
                comenzarTurno(jugador.getId_jugador());
                break;
            case PENALIZACION:
                // Casilla de penalización "¡QUÉ BURRO SOY!"
                // Implementamos la lógica según las reglas del juego
                jugadorService.marcarTurnoPerdido(jugador.getId_jugador());
                break;
            case RETROCESO:
                // Casilla de retroceso "COZ DEL BURRO"
                // El jugador retrocede tres casillas
                int nuevaPosicionRetroceso = jugador.getPosicion_jugador() - 3;
                if (nuevaPosicionRetroceso < 1) {
                    // Si la nueva posición es menor que 1, retrocedemos hasta la casilla de salida
                    nuevaPosicionRetroceso = 1;
                }
                jugador.setPosicion_jugador(nuevaPosicionRetroceso);
                // jugadorRepository.save(jugador);
                break;
            case LLEGADA:
                // El jugador ha llegado a la casilla de llegada, lo declaramos como ganador
                // Implementamos la lógica para declarar al jugador como ganador según las reglas del juego
                declararGanador(jugador);
                break;
            default:
                // No hay casilla especial en esta posición, simplemente avanzamos
                break;
        }
    }

    @Override
    public void presentarCartaAlJugador(JugadorInfo jugador, QuestionsAnswersInfo pregunta) {
         // Aquí implementamos la lógica para presentar la carta al jugador
        // Por ejemplo, podríamos enviar la carta al cliente a través de una API REST
        // O podríamos mostrar la carta en la interfaz de usuario si estamos desarrollando una aplicación web o móvil
        // En esta implementación de ejemplo, simplemente imprimimos la pregunta de la carta por consola
        
        System.out.println("¡Es hora de una pregunta para " + jugador.getNombre_jugador() + "!");
        System.out.println("Categoría: " + pregunta.getResults_category_questions());
        System.out.println("Pregunta: " + pregunta.getResults_question_questions());
        System.out.println("Respuestas:");
    
        // Mostramos las posibles respuestas de la carta
        List<String> respuestas = pregunta.getResults_incorrect_answers_questions();
        respuestas.add(pregunta.getResults_correct_answer_questions()); // Agregamos la respuesta correcta a la lista
        Collections.shuffle(respuestas); // Barajamos las respuestas para que no estén en el mismo orden siempre
        for (String respuesta : respuestas) {
            System.out.println("- " + respuesta);
        }
    
        // Aquí recopilamos la respuesta del jugador
        // Supongamos que tienes una lógica para obtener la respuesta del jugador, ya sea desde una interfaz de usuario, una solicitud HTTP, etc.
        // Una vez que obtengas la respuesta del jugador, la pasas al método jugadorRespondeCorrectamente
        String respuestaJugador = obtenerRespuestaDelJugador();
    
        // Verificamos si el jugador responde correctamente
        if (jugadorRespondeCorrectamente(respuestaJugador, pregunta)) {
            lanzarDadoMovimiento(jugador);
        }
    }

    @Override
    public void declararGanador(JugadorInfo jugador) {
       // Implementamos la lógica para declarar al jugador como ganador
        // Esto podría incluir finalizar el juego y mostrar un mensaje de victoria
        System.out.println("¡" + jugador.getNombre_jugador() + " ha llegado a la casilla de llegada y es el ganador!");
        System.out.println("¡Felicidades!");

        // Aquí podríamos agregar más acciones dependiendo de la aplicación, como reiniciar el juego, registrar la victoria en una base de datos, etc.
    }

    @Override
    public boolean jugadorRespondeCorrectamente(String respuestaJugador, QuestionsAnswersInfo pregunta) {
        // Lógica para determinar si el jugador responde correctamente la pregunta
        // Aquí compararías la respuesta del jugador con la respuesta correcta de la carta
        // En este caso, la respuesta correcta está almacenada directamente en la carta

        // Obtenemos la respuesta correcta de la carta
        String respuestaCorrecta = pregunta.getResults_correct_answer_questions();

        // Comparamos la respuesta del jugador con la respuesta correcta
        return respuestaJugador.equalsIgnoreCase(respuestaCorrecta);
    }
    // Método para obtener la respuesta del jugador (ejemplo básico)
    private String obtenerRespuestaDelJugador() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa tu respuesta: ");
        return scanner.nextLine().trim(); // Devuelve la respuesta ingresada por el jugador, eliminando espacios al inicio y al final
    }

    @Override
    public void lanzarDadoMovimiento(JugadorInfo jugador) {
        // Obtenemos el valor del dado de movimiento (1 a 4)
        int valorDadoMovimiento = dadoMovimientoService.lanzarDadoMovimiento();

        // Obtenemos la posición actual del jugador en el tablero
        int posicionActual = jugador.getPosicion_jugador();
        
        // Calculamos la nueva posición sumando el valor del dado de movimiento
        int nuevaPosicion = posicionActual + valorDadoMovimiento;

        // Verificamos si la nueva posición excede el límite del tablero
        int limiteTablero = 17; // Número total de casillas en el tablero
        if (nuevaPosicion > limiteTablero) {
            // El jugador se ha pasado del final del tablero, no realizamos el movimiento
            return;
        }

        // Actualizamos la posición del jugador en el tablero
        jugador.setPosicion_jugador(nuevaPosicion);
        // jugadorRepository.save(jugador);

        // Verificamos la casilla en la que ha caído el jugador
        verificarCasilla(jugador);
    }
    // Otros métodos según sea necesario

}
