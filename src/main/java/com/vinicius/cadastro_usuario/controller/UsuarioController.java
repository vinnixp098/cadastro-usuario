package com.vinicius.cadastro_usuario.controller;

import com.vinicius.cadastro_usuario.busines.UsuarioService;
import com.vinicius.cadastro_usuario.insfratructure.entitys.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario){
        usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
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
