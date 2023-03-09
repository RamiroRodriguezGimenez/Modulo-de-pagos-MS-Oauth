package com.serviceoauth.security;


import com.commons.clients.models.entity.Client;
import com.serviceoauth.feignclients.ClientFeignClient;
import com.serviceoauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private UserService usuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<String, Object>();

        Client usuario = usuarioService.findByEmail(authentication.getName());
        info.put("nombre", usuario.getFirstName());
        info.put("apellido", usuario.getLastName());
        info.put("correo", usuario.getEmail());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}
