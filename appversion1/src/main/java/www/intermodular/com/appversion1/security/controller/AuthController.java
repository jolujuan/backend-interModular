package www.intermodular.com.appversion1.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;

import www.intermodular.com.appversion1.security.dto.JwtDto;
import www.intermodular.com.appversion1.security.dto.LoginUsuario;
import www.intermodular.com.appversion1.security.dto.Mensaje;
import www.intermodular.com.appversion1.security.dto.NuevoUsuario;
import www.intermodular.com.appversion1.security.entity.RolDb;
import www.intermodular.com.appversion1.security.entity.UsuarioDb;
import www.intermodular.com.appversion1.security.entity.enums.RolNombre;
import www.intermodular.com.appversion1.security.jwt.JwtService;
import www.intermodular.com.appversion1.security.service.RolService;
import www.intermodular.com.appversion1.security.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtService jwtService;

    @Autowired
    JwtService jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        {
            if (bindingResult.hasErrors())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Mensaje("Datos incorrectos o email inválido"));
            if (usuarioService.existsByNickname(nuevoUsuario.getNickname()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Ese nickname ya existe"));
            if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Ese email ya existe"));

            UsuarioDb usuarioDb = new UsuarioDb(nuevoUsuario.getNombre(), nuevoUsuario.getNickname(),
                    nuevoUsuario.getEmail(),
                    passwordEncoder.encode(nuevoUsuario.getPassword()));
            Set<RolDb> rolesDb = new HashSet<>();
            /* rolesDb.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get()); */
            
            try {
                RolDb rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
                rolesDb.add(rolUser);
        
                if (nuevoUsuario.getRoles().contains("admin")) {
                    RolDb rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
                    rolesDb.add(rolAdmin);
                }
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
            }

            usuarioDb.setRoles(rolesDb);
            usuarioService.save(usuarioDb);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Usuario creado"));

        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Datos incorrectos"));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNickname(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return ResponseEntity.status(HttpStatus.OK).body(jwtDto);
    }
}