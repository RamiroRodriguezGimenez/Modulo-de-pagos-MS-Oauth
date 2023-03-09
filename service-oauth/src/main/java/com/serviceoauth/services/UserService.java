package com.serviceoauth.services;





import com.commons.clients.models.entity.Client;
import com.serviceoauth.feignclients.ClientFeignClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserService implements UserDetailsService, IUsuarioService {

    private Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    ClientFeignClient clientFeign;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Client usuario = clientFeign.findByEmail(email);

            List<GrantedAuthority> authorities = usuario.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .peek(authority -> log.info("Role: " + authority.getAuthority())).collect(Collectors.toList());




            return new User(usuario.getEmail(), usuario.getPassword(), usuario.isActive(), true, true, true,
                    authorities);

        } catch (FeignException e) {
            String error = "Error en el login, no existe el usuario '" + email + "' en el sistema";


            throw new UsernameNotFoundException(error);
        }
    }

    @Override
    public Client findByEmail(String email) {
        return clientFeign.findByEmail(email);
    }
}
