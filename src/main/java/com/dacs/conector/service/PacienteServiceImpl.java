package com.dacs.conector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.dacs.conector.api.client.PacienteClient;
import com.dacs.conector.dto.PacienteDto;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteClient pacienteClient;

    @Override
    public PacienteDto getPacientes(int cantidad, String nacionalidad) {
        

        PacienteDto response = pacienteClient.search(cantidad, nacionalidad);

        return response;
    }

}
