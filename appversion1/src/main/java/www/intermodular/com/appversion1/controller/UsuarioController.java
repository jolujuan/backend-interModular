package www.intermodular.com.appversion1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import www.intermodular.com.appversion1.model.dto.Usuario;
import www.intermodular.com.appversion1.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/obtener/{id}")
    public ResponseEntity<byte[]> obtenerImagenUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null && usuario.getImagen() != null) {
            return ResponseEntity.ok(usuario.getImagen());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar-imagen/{id}")
    public ResponseEntity<String> eliminarImagenUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setImagen(null);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("Imagen eliminada correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

