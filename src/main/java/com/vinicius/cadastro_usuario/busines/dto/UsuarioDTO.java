package com.vinicius.cadastro_usuario.busines.dto;

import com.vinicius.cadastro_usuario.insfratructure.entitys.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String nome;
    private String email;
    private String cpf;

    public static UsuarioDTO from(Usuario usuario) {
        return UsuarioDTO.builder()
                .cpf(usuario.getCpf())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
}
