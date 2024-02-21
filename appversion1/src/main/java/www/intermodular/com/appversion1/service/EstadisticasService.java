package www.intermodular.com.appversion1.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface EstadisticasService {

    public Map<String, String>  getStatsUser(Long idPregunta);
} 