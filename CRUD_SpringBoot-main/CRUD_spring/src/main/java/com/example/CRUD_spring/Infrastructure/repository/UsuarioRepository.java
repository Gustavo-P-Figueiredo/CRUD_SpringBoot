package com.example.CRUD_spring.Infrastructure.repository;

import com.example.CRUD_spring.Infrastructure.entitys.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);


    @Transactional
    void deleteByEmail(String email);

    boolean existsByEmail(@Email(message = "Email invalido, por favor insira novamente!") @NotBlank(message = "Por favor informe um email") String email);
}
