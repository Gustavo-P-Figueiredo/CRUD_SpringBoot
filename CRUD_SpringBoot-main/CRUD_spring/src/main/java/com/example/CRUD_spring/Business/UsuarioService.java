package com.example.CRUD_spring.Business;


import com.example.CRUD_spring.Infrastructure.entitys.Usuario;
import com.example.CRUD_spring.Infrastructure.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario) {
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
        repository.saveAndFlush(usuario);
    }

    @Transactional(readOnly = true)

    public Usuario buscarUsuarioPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email não encontrado"));
    }

    public void deletarUsuarioPorEmail(String email) {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email não encontrado"));
        repository.deleteByEmail(email);
    }

    public Usuario atualizarUsuarioPorId(Integer id, Usuario dadosAtualizados) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (dadosAtualizados.getEmail() != null && !dadosAtualizados.getEmail().equals(usuario.getEmail())) {
            if (repository.existsByEmail(dadosAtualizados.getEmail())) {
                throw new DataIntegrityViolationException("E-mail já está em uso");
            }
            usuario.setEmail(dadosAtualizados.getEmail());
        }

        if (dadosAtualizados.getNome() != null)
            usuario.setNome(dadosAtualizados.getNome());

        if (dadosAtualizados.getSenha() != null)
            usuario.setSenha(dadosAtualizados.getSenha());

        if (dadosAtualizados.getCep() != null)
            usuario.setCep(dadosAtualizados.getCep());

        return repository.save(usuario);
    }
}


