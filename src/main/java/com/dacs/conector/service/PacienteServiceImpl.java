package com.dacs.conector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.dacs.conector.api.client.PacienteClient;
import com.dacs.conector.dto.PacienteDto;
import com.dacs.conector.dto.PaginacionDto;
import com.dacs.conector.dto.KeycloakUserDto;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteClient pacienteClient;

    @Autowired
    @Qualifier("keycloakRestTemplate") // Usa el RestTemplate configurado
    private RestTemplate keycloakRestTemplate;

    @Value("${keycloak.auth-server-url}")
    private String keycloakBaseUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    // Ya no necesitas estas properties porque están en KeycloakConfig
    // @Value("${keycloak.resource}")
    // private String keycloakClientId;
    // @Value("${keycloak.credentials.secret}")
    // private String keycloakClientSecret;

    // Ya no necesitas crear un RestTemplate aquí
    // private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public PacienteDto getPacientes(int cantidad, String nacionalidad) {
        PacienteDto response = pacienteClient.search(cantidad, nacionalidad);
        return response;
    }

    @Override
    public PaginacionDto.Response<KeycloakUserDto> getUsers(int page, int size, String search) {
        // Ya no necesitas obtener el token manualmente, el interceptor lo hace
        String url = keycloakBaseUrl + "/admin/realms/" + keycloakRealm + "/users";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("first", page * size)
            .queryParam("max", size);
        
        if (search != null && !search.isEmpty()) {
            builder.queryParam("search", search);
        }

        // Ya no necesitas agregar el header de Authorization manualmente
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<KeycloakUserDto[]> response = keycloakRestTemplate.exchange(
            builder.toUriString(),
            HttpMethod.GET,
            entity,
            KeycloakUserDto[].class
        );

        List<KeycloakUserDto> users = Arrays.asList(response.getBody());
        PaginacionDto.Response<KeycloakUserDto> paginacion = new PaginacionDto.Response<>();
        paginacion.setPagina(page);
        paginacion.setTamaño(size);
        paginacion.setContenido(users);
        
        return paginacion;
    }

    // Ya no necesitas este método porque KeycloakConfig lo maneja
    // private String getKeycloakToken() { ... }
}