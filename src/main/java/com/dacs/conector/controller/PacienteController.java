package com.dacs.conector.controller;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dacs.conector.dto.KeycloakUserDto;
import com.dacs.conector.dto.PacienteDto;
import com.dacs.conector.dto.PaginacionDto;
import com.dacs.conector.service.PacienteService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;

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

}