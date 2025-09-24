package com.example.CRUD_spring.Infrastructure.repository;

import com.example.CRUD_spring.Infrastructure.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> { //JpaRepository<*NomedaTabela*, *TipodoID*>





}
