package com.vinicius.cadastro_usuario.controller;

import com.vinicius.cadastro_usuario.busines.dto.UsuarioDTO;
import com.vinicius.cadastro_usuario.busines.service.UsuarioService;
import com.vinicius.cadastro_usuario.insfratructure.entitys.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar-usuario")
    public ResponseEntity<?> salvarUsuario(@Valid @RequestBody Usuario usuario){
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping("/buscar-usuario-email")
    public ResponseEntity<?> buscarUsuarioPorEmail(@RequestParam String email){
        Optional<UsuarioDTO> exiteEsseEmail = usuarioService.buscarUsuarioPorEmail(email);

        if(exiteEsseEmail.isPresent()){
            return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));

        }
        return new ResponseEntity<>("Usuário não encontrato! Utilize outro email.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar-usuario-cpf")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorCpf(@RequestParam String cpf){
        Optional<UsuarioDTO> usuarioDTOOptional = (Optional<UsuarioDTO>) usuarioService.buscarUsuarioPorCpf(cpf);

        if (usuarioDTOOptional.isPresent()) {
            return ResponseEntity.ok(usuarioDTOOptional.get());
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar-usuarios")
    public ResponseEntity<List<Usuario>> buscarUsuarios(){
        return ResponseEntity.ok(usuarioService.buscarUsuarios());
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUusuarioPorEmail(@RequestParam String email){
        usuarioService.deletarPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarUsuarioPorEmail(@RequestParam String email, @RequestBody Usuario usuario){
        usuarioService.atualizarUsuarioPorEmail(email, usuario);
        return ResponseEntity.ok().build();
    }
}
