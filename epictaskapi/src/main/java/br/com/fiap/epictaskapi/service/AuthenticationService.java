package br.com.fiap.epictaskapi.service;

import java.util.Optional;

import br.com.fiap.epictaskapi.model.Usuario;
import br.com.fiap.epictaskapi.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(username);
        if (usuario.isPresent()) return usuario.get();
        throw new UsernameNotFoundException("usuario não encontrado " + username);
    }
    
}
