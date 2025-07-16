package com.vinicius.cadastro_usuario.controller;

import com.vinicius.cadastro_usuario.busines.UsuarioService;
import com.vinicius.cadastro_usuario.insfratructure.entitys.Usuario;
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
    public ResponseEntity<String> salvarUsuario(@RequestBody Usuario usuario){

        Optional<Usuario> exiteComEsseEmail = Optional.ofNullable(usuarioService.buscarUsuarioPorEmail(usuario.getEmail()));

        if(exiteComEsseEmail.isPresent()){
            return new ResponseEntity<>("Email já cadastrado! Utilize outro email.", HttpStatus.BAD_REQUEST);
//            return (ResponseEntity<String>) ResponseEntity.badRequest();
        }

        usuarioService.salvarUsuario(usuario);
        return new ResponseEntity<>("Usuário cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/buscar-usuario-email")
    public ResponseEntity<?> buscarUsuarioPorEmail(@RequestParam String email){
        Optional<Usuario> exiteEsseEmail = Optional.ofNullable(usuarioService.buscarUsuarioPorEmail(email));

        if(exiteEsseEmail.isEmpty()){
            return new ResponseEntity<>("Usuário não encontrato! Utilize outro email.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @GetMapping("/buscar-usuario-cpf")
    public ResponseEntity<?> buscarUsuarioPorCpf(@RequestParam String cpf){
        Optional<Usuario> exiteEsseEmail = Optional.ofNullable(usuarioService.buscarUsuarioPorCpf(cpf));

        if(exiteEsseEmail.isEmpty()){
            return new ResponseEntity<>("Usuário não encontrato! Utilize outro email.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorCpf(cpf));
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
