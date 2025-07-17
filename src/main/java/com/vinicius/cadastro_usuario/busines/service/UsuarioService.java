package com.vinicius.cadastro_usuario.busines.service;

import com.vinicius.cadastro_usuario.busines.dto.UsuarioDTO;
import com.vinicius.cadastro_usuario.busines.interfaces.UsuarioInterface;
import com.vinicius.cadastro_usuario.insfratructure.entitys.Usuario;
import com.vinicius.cadastro_usuario.insfratructure.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Boolean salvarUsuario(Usuario usuario){

        Optional<UsuarioInterface> usuarioViewOptional = repository.findByCpf(usuario.getCpf());
        if (usuarioViewOptional.isPresent()) {
            return false;
        }

        repository.saveAndFlush(usuario);
        return true;
    }

    public Optional<UsuarioDTO> buscarUsuarioPorEmail(String email){
        Optional<UsuarioInterface> usuarioViewOptional = repository.findByCpf(email);
        if (usuarioViewOptional.isPresent()) {
            UsuarioInterface usuarioInterface = usuarioViewOptional.get();
            UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                    .nome(usuarioInterface.getNome())
                    .email(usuarioInterface.getEmail())
                    .cpf(usuarioInterface.getCpf())
                    .build();
            return Optional.of(usuarioDTO);
        } else {
            return Optional.empty();
        }
    }

    public Optional<UsuarioDTO> buscarUsuarioPorCpf(String cpf){
        Optional<UsuarioInterface> usuarioViewOptional = repository.findByCpf(cpf);
        if (usuarioViewOptional.isPresent()) {
            UsuarioInterface usuarioInterface = usuarioViewOptional.get();
            UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                    .nome(usuarioInterface.getNome())
                    .email(usuarioInterface.getEmail())
                    .cpf(usuarioInterface.getCpf())
                    .build();
            return Optional.of(usuarioDTO);
        } else {
            return Optional.empty();
        }
    }

    public List<Usuario> buscarUsuarios(){
        return repository.findAll();
    }

    public void deletarPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public  void atualizarUsuarioPorEmail(String email, Usuario usuario){
        Optional<UsuarioDTO> usuarioEntity = buscarUsuarioPorEmail(email);
        Usuario usuarioAtualizdo = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.get().getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.get().getNome())
                .cpf(usuario.getCpf())
                .build();

        repository.saveAndFlush(usuarioAtualizdo);

    }
}
