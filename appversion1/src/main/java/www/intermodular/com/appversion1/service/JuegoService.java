package www.intermodular.com.appversion1.service;

import www.intermodular.com.appversion1.model.dto.JugadorInfo;
import www.intermodular.com.appversion1.model.dto.QuestionsAnswersInfo;

public interface JuegoService {

    // Método para iniciar un nuevo juego
    public void iniciarNuevoJuego();

    // Método para realizar un movimiento en el tablero
    public void comenzarTurno(Long jugadorId);

    // Método para verificar la casilla en la que ha caído el jugador
    public void verificarCasilla(JugadorInfo jugador);
   
    // Método para lanzar el dado de movimiento y mover al jugador en el tablero
    public void lanzarDadoMovimiento(JugadorInfo jugador);
   
     // Método para presentar una pregunta al jugador para que responda
     public void presentarCartaAlJugador(JugadorInfo jugador, QuestionsAnswersInfo pregunta);
    // Método para declarar al jugador como ganador
    public void declararGanador(JugadorInfo jugador);
    // Método para verificar si el jugador responde correctamente la pregunta
    public boolean jugadorRespondeCorrectamente(String respuestaJugador, QuestionsAnswersInfo pregunta);
}