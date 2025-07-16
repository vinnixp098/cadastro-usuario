package com.vinicius.cadastro_usuario.controller;

import com.vinicius.cadastro_usuario.busines.UsuarioService;
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
    public ResponseEntity<String> salvarUsuario(@Valid @RequestBody Usuario usuario){

        Optional<Usuario> exiteComEsseEmail = usuarioService.buscarUsuarioPorCpf(usuario.getCpf());

        if(exiteComEsseEmail.isPresent()){
            return new ResponseEntity<>("CPF já cadastrado! Utilize outro CPF.", HttpStatus.BAD_REQUEST);
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
        Optional<Optional<Usuario>> exiteEsseEmail = Optional.ofNullable(usuarioService.buscarUsuarioPorCpf(cpf));

        if(exiteEsseEmail.isEmpty()){
            return new ResponseEntity<>("Usuário não encontrato! Utilize outro CPF.", HttpStatus.NOT_FOUND);
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
