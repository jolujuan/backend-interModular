package www.intermodular.com.appversion1.service;

import www.intermodular.com.appversion1.model.dto.CasillaList;
import www.intermodular.com.appversion1.model.dto.TableroInfo;

public interface TableroService {
    
    public Long getIdUserJugador(String nickname);
    public String getStartTablero(Long idUsuario);
    public String getAnotherPlayer(String nickname,Long idTablero);
    public String getTablerostatus(Long idTablero);
    public String getStartGame(Long idTablero);
    public String getRollDice(String nickname, Long idTablero);
    public String movePlayer(String nickname, Long numBoxMove ,Long idTable);
}