
package www.intermodular.com.appversion1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.intermodular.com.appversion1.service.EstadisticasService;
import www.intermodular.com.appversion1.service.TableroService;

@RestController
@RequestMapping("/api/v1")
public class EstadisticasController {
    private final EstadisticasService estadisticasService;
    private final TableroService tableroService;

 


      public EstadisticasController(EstadisticasService estadisticasService, TableroService tableroService) {
        this.estadisticasService = estadisticasService;
        this.tableroService = tableroService;
    }




    @GetMapping("/getStatsPlayer/{nickname}")
    public ResponseEntity<Map<String, String>>  getStatsTablero(@PathVariable String nickname) {
        try {
            Map<String, String> response =  estadisticasService.getStatsUser(tableroService.getIdUserJugador(nickname));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);


            
        }
    }
}