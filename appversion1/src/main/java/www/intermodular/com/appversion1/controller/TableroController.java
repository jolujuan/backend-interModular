package www.intermodular.com.appversion1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.intermodular.com.appversion1.repository.TableroRepository;
import www.intermodular.com.appversion1.service.TableroService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1")
public class TableroController {
    private final TableroService tableroService;

    public TableroController(TableroService tableroService) {
        this.tableroService = tableroService;
    }


    //StartGame
    @PostMapping("/startGame/{idTablero}")
    public ResponseEntity<Map<String, String>>  startGame(@PathVariable Long idTablero) {
        try {
            Map<String, String> response = new HashMap<>();
            String idTableroFind = tableroService.getStartGame( idTablero);
            if (idTableroFind.split(" ").length>0) {
                response.put("idTablero ", idTableroFind.split(" ")[1]);
                response.put("State_Game", idTableroFind.split(" ")[3]);
            }else{
                response.put("ERROR ", "GAME_CANT_START");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);


            
        }
    }
    //Rolldice tirar el dado  y comprobar si la tirada es valida

    @GetMapping("/rolldice/{nickname}/table/{idTablero}")
    public ResponseEntity<Map<String, String>>  rolldice(@PathVariable String nickname,@PathVariable Long idTablero) {
        try {
            Map<String, String> response = new HashMap<>();

            String tiradaDado = tableroService.getRollDice( nickname,idTablero);

           
                response.put("RollDice ", tiradaDado);
          
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);


            
        }
    }
    

    @PostMapping("/movePlayer/{nickname}/number/{numBoxMove}/table/{idTable}")
    public ResponseEntity<Map<String, String>>  movePlayer(@PathVariable String nickname,@PathVariable Long numBoxMove,@PathVariable Long idTable) {
        try {
            Map<String, String> response = new HashMap<>();

            String tiradaDado = tableroService.getRollDice( nickname,numBoxMove);

           
                response.put("RollDice ", tiradaDado);
          
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);


            
        }
    }
    
    

    @GetMapping("/getStatusBoard/{idTablero}")
    public ResponseEntity<Map<String, String>>  getStatusBoard(@PathVariable Long idTablero) {
        try {
            Map<String, String> response = new HashMap<>();
            String idTableroFind = tableroService.getTablerostatus(idTablero);
            if (idTableroFind.split(" ").length>4) {
                response.put("idTablero ", idTableroFind.split(" ")[1]);
            response.put("Player 1 ", idTableroFind.split(" ")[3]);
            response.put("Player 2 ", idTableroFind.split(" ")[5]);
            response.put("Player 3 ", idTableroFind.split(" ")[7]);
            response.put("Player 4 ", idTableroFind.split(" ")[9]);
            }else{
                response.put("ERROR ", "SOME_ERROR_IN_TABLERO");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);


            
        }
    }
    
    @PostMapping("/createBoard/{nickname}")
    public ResponseEntity<Map<String, String>> getStartGame(@PathVariable String nickname) {
        try {
            Map<String, String> response = new HashMap<>();
            Long idUserPlayer = tableroService.getIdUserJugador(nickname);
            String idTableroCreado = tableroService.getStartTablero(idUserPlayer);

            response.put("idTablero", idTableroCreado.split(" ")[1]); // conger solo el long del id
            response.put("Player 1", idTableroCreado.split(" ")[3]);// que devuelva el nombre jugador
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addPlayer/name/{nickname}/table/{idTablero}")
    public ResponseEntity<Map<String, String>> addPlayer(@PathVariable String nickname,@PathVariable Long idTablero) {
        try {
            Map<String, String> response = new HashMap<>();
            String idTableroCreado = tableroService.getAnotherPlayer(nickname, idTablero);
            if (idTableroCreado.split(" ").length>4) {
                response.put("idTablero ", idTableroCreado.split(" ")[1]);
            response.put("Player 1 ", idTableroCreado.split(" ")[3]);
            response.put("Player 2 ", idTableroCreado.split(" ")[5]);
            response.put("Player 3 ", idTableroCreado.split(" ")[7]);
            response.put("Player 4 ", idTableroCreado.split(" ")[9]);
            }else{
                response.put("ERROR ", idTableroCreado.split(" ")[1]);
            }

            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}