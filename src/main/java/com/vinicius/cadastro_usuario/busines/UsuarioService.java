package com.vinicius.cadastro_usuario.busines;

import com.vinicius.cadastro_usuario.insfratructure.entitys.Usuario;
import com.vinicius.cadastro_usuario.insfratructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service

public class UsuarioService {

     private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email nao encontrado")
        );
    }

    public void deletarPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public  void atualizarUsuarioPorEmail(String email, Usuario usuario){
        Usuario usuarioEntity = buscarUsuarioPorEmail(email);
        Usuario usuarioAtualizdo = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
                .ID(usuarioEntity.getID())
                .build();

        repository.saveAndFlush(usuarioAtualizdo);

    }
}
