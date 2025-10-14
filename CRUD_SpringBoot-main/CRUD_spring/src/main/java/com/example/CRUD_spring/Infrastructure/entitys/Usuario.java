package com.example.CRUD_spring.Infrastructure.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Usuario")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Por favor informe um email")
    @Email(message = "Email invalido, por favor insira novamente!")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Por favor informe um nome")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank(message = "Por favor informe uma senha")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Column(name = "senha", nullable = false)
    private String senha;

    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos numéricos")
    @Column(name = "cep")
    private String cep;

    public Usuario orElseThrow(Object usuárioNãoEncontrado) {
        return null;
    }
}
