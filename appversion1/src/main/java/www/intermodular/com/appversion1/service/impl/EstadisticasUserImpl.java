package www.intermodular.com.appversion1.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import www.intermodular.com.appversion1.model.db.UsuariosEstadisticasDb;
import www.intermodular.com.appversion1.repository.UsuariosEstadisticasRepository;
import www.intermodular.com.appversion1.service.EstadisticasService;

@Service
public class EstadisticasUserImpl implements EstadisticasService{
   
    private final UsuariosEstadisticasRepository usuariosEstadisticasRepository;
    
    public EstadisticasUserImpl(UsuariosEstadisticasRepository usuariosEstadisticasRepository) {
        this.usuariosEstadisticasRepository = usuariosEstadisticasRepository;
    }

    @Override
    public  Map<String, String>  getStatsUser(Long idUser) {
        Map<String, String> response =new HashMap<>();
        UsuariosEstadisticasDb user= usuariosEstadisticasRepository.findByIdUsuario(idUser);
        response.put("StatsUser","" );
        response.put("ID",""+user.getIdUsuario() );
        response.put("PreguntasAcertadas",""+user.getPreguntasAcertadas() );
        response.put("PreguntasFalladas",""+user.getPreguntasFalladas() );
        response.put("PreguntasTotales",""+user.getPreguntasTotales() );
        return  response;
    }
 
  
  
}