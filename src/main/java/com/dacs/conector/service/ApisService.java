package com.dacs.conector.service;

import com.dacs.conector.dto.PacienteDto;

import com.dacs.conector.dto.PaginacionDto;
import com.dacs.conector.dto.KeycloakUserDto;

public interface ApisService {

    PacienteDto getPacientes(int cantidad, String nacionalidad);

    PaginacionDto<KeycloakUserDto> getUsers(int page, int size, String search);

    String createUser(KeycloakUserDto.Create userDto);

    KeycloakUserDto updateUser(String id, KeycloakUserDto.Update userDto);

    KeycloakUserDto updateUserStatus(String id, Boolean status);

    KeycloakUserDto getUserById(String id);
}
