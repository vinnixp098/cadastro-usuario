package com.vinicius.cadastro_usuario.insfratructure.repository;

import com.vinicius.cadastro_usuario.busines.dto.UsuarioDTO;
import com.vinicius.cadastro_usuario.busines.interfaces.UsuarioInterface;
import com.vinicius.cadastro_usuario.insfratructure.entitys.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<UsuarioInterface> findByEmail(String email);

    Optional<UsuarioInterface> findByCpf(String cpf);

    @Transactional
    void deleteByEmail(String email);

}
