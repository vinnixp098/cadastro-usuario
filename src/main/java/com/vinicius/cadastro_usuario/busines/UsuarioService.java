package com.vinicius.cadastro_usuario.busines;

import com.vinicius.cadastro_usuario.insfratructure.entitys.Usuario;
import com.vinicius.cadastro_usuario.insfratructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                () -> new RuntimeException("Usuário nao encontrado")
        );
    }

    public Usuario buscarUsuarioPorCpf(String cpf){
        return repository.findByCpf(cpf).orElseThrow(
                () -> new RuntimeException("Usuário nao encontrado")
        );
    }

    public List<Usuario> buscarUsuarios(){
        return repository.findAll();
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
