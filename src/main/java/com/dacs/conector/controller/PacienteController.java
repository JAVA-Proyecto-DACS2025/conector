package com.dacs.conector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.dacs.conector.dto.ApiResponseDto;
import com.dacs.conector.dto.KeycloakUserDto;
import com.dacs.conector.dto.PacienteDto;
import com.dacs.conector.dto.PaginacionDto;
import com.dacs.conector.service.PacienteService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Slf4j
@RequestMapping("/api/external")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("paciente")
    public PacienteDto searchPaciente(@RequestParam("cantidad") int cantidad,
            @RequestParam("nacionalidad") String nacionalidad) {
        PacienteDto pacienteDto = pacienteService.getPacientes(cantidad, nacionalidad);

        return pacienteDto;
    }

    @GetMapping("users")
    public PaginacionDto<KeycloakUserDto> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "search", required = false, defaultValue = "") String search) {
        log.info("GET /api/external/users called");
        return pacienteService.getUsers(page, size, search);
    }

    @PostMapping("users")
    public ApiResponseDto<KeycloakUserDto> createUser(@RequestBody KeycloakUserDto.Create userDto) {
        log.info("POST /api/external/users called");
        return ApiResponseDto.<KeycloakUserDto>builder()
                .success(true)
                .data(null)
                .message(pacienteService.createUser(userDto))
                .build();  
    }

    @PutMapping("users/{id}")
    public ApiResponseDto<KeycloakUserDto> updateUser(@PathVariable("id") String id, 
            @RequestBody KeycloakUserDto.Update userDto) {
        log.info("PUT /api/external/users/{} called", id);
        KeycloakUserDto updatedUser = pacienteService.updateUser(id, userDto);
        return ApiResponseDto.<KeycloakUserDto>builder()
                .success(true)
                .data(updatedUser)
                .message("Usuario actualizado correctamente")
                .build();
    }
}