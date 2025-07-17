package com.vinicius.cadastro_usuario.insfratructure.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuario")
@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    @Column(name = "email", unique = true)
    @NotBlank(message = "O email não ser nulo ou vazio.")
    private String email;

    @Column(name = "cpf", unique = true)
    @NotBlank(message = "O cpf não ser nulo ou vazio.")
    private String cpf;

    @Column(name = "nome")
    @NotBlank(message = "O nome não ser nulo ou vazio.")
    private String nome;

}

