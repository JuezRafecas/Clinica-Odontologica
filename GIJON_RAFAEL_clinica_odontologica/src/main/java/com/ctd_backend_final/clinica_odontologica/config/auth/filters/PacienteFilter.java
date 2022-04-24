package com.ctd_backend_final.clinica_odontologica.config.auth.filters;

import com.ctd_backend_final.clinica_odontologica.security.components.JwtUtil;
import com.ctd_backend_final.clinica_odontologica.security.entity.UserRoles;
import com.ctd_backend_final.clinica_odontologica.security.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PacienteFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.isUserInRole(UserRoles.ROLE_USER.name())) { // <- si soy USER

            Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String[] parts = request.getRequestURI().split("/");
        }
        filterChain.doFilter(request, response);
    }
}
