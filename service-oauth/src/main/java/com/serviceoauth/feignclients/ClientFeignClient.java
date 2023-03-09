package com.serviceoauth.feignclients;


import com.commons.clients.models.entity.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="client-service")
public interface ClientFeignClient {

    @GetMapping ("/api/client/getByEmail")
    public Client findByEmail(@RequestParam String email);
}
