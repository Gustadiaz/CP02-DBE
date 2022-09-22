package br.com.fiap.epictaskapi.service;



import br.com.fiap.epictaskapi.dto.UsuarioDtoNoPassword;
import br.com.fiap.epictaskapi.model.Usuario;
import br.com.fiap.epictaskapi.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public Page<Usuario> listAll(Pageable pagina) {
        return usuarioRepository.findAll(pagina);
    }

    public Optional<Usuario> getById(Long id) {
        return usuarioRepository.findById(id);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void remove(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDtoNoPassword convertDto(Usuario usuario) {
        UsuarioDtoNoPassword dto = new UsuarioDtoNoPassword();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        
        return dto;
    }

    public List<UsuarioDtoNoPassword> listDtoUsuario(Long id) {
        return usuarioRepository.findById(id)
                                .stream()
                                .map(this::convertDto)
                                .collect(Collectors.toList());
    }

}
