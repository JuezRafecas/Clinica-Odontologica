package com.ctd_backend_final.clinica_odontologica.security;

import com.ctd_backend_final.clinica_odontologica.security.entity.Usuario;
import com.ctd_backend_final.clinica_odontologica.security.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private IUserRepository userRepository;

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = userRepository.findByUsername(username);
        if (usuario.isPresent()) {
            return (UserDetails) usuario.get();
        }
        throw new UsernameNotFoundException("Usuario no encontrado");
    }
}
