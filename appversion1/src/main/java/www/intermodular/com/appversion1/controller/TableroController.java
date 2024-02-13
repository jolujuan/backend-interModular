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

@RestController
@RequestMapping("/api/v1")
public class TableroController {
    private final TableroService tableroService;

    public TableroController(TableroService tableroService) {
        this.tableroService = tableroService;
    }

    @PostMapping("/createBoard/{nickname}")
    public ResponseEntity<Map<String, String>> getStartGame(@PathVariable String nickname) {
        try {
            Map<String, String> response = new HashMap<>();
            Long  idUserPlayer = tableroService.getIdUserTablero(nickname);
            String idTableroCreado = tableroService.getStartTablero(idUserPlayer);

            response.put("idTablero", idTableroCreado.split(" ")[1]); // conger solo el long del id
            response.put("Player 1:",idTableroCreado.split(" ")[3]);//que devuelva el nombre jugador
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}