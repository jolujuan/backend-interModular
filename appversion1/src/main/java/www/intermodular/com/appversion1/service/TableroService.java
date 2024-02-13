package www.intermodular.com.appversion1.service;

import www.intermodular.com.appversion1.model.dto.CasillaList;
import www.intermodular.com.appversion1.model.dto.TableroInfo;

public interface TableroService {
    
    public Long getIdUserTablero(String nickname);
    public String getStartTablero(Long idUsuario);
    public String getAnotherPlayer(String nickname,String player,Long idTablero);
}