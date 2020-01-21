package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Universidade;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class UniversidadeService {

    RestTemplate restTemplate=new RestTemplate();

    public Universidade getUniversidade() {

        return Universidade.getUniversidade();

    }

    public Optional<Universidade> sendUniversidade() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Universidade> payload=new HttpEntity<>(Universidade.getUniversidade(),headers);

        try{
            ResponseEntity<Universidade>  universidadeResponseEntity =restTemplate.postForEntity("http://127.0.0.1:8080/universidade",payload,Universidade.class);
            if(universidadeResponseEntity.hasBody()) {
                return Optional.of(universidadeResponseEntity.getBody());
            }
        }catch(HttpClientErrorException exc){
            System.out.println("Error");
        }

        return Optional.empty();
    }
}
