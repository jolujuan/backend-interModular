package edu.alumno.ismael.apr_rest_mysql_futbol.security.controller;

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

import edu.alumno.ismael.apr_rest_mysql_futbol.security.dto.JwtDto;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.dto.LoginUsuario;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.dto.Mensaje;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.dto.NuevoUsuario;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.entity.RolDb;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.entity.UsuarioDb;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.entity.enums.RolNombre;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.jwt.JwtService;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.service.RolService;
import edu.alumno.ismael.apr_rest_mysql_futbol.security.service.UsuarioService;

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
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Datos incorrectos o email inválido"));
            if (usuarioService.existsByNickname(nuevoUsuario.getNickname()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Ese nickname ya existe"));
            if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Ese email ya existe"));

            UsuarioDb usuarioDb =
                     new UsuarioDb(nuevoUsuario.getNombre(), nuevoUsuario.getNickname(),
                    nuevoUsuario.getEmail(),
                    passwordEncoder.encode(nuevoUsuario.getPassword()));
            Set<RolDb> rolesDb = new HashSet<>();
            rolesDb.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
            if (nuevoUsuario.getRoles().contains("admin"))
                rolesDb.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
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