package com.serviceoauth.services;


import com.commons.clients.models.entity.Client;

public interface IUsuarioService {

    public Client findByEmail (String email);
}
