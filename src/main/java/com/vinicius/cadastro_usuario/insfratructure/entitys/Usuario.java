package com.vinicius.cadastro_usuario.insfratructure.entitys;

import jakarta.persistence.*;
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
    private String email;

    @Column(name = "nome")
    private String nome;

}

