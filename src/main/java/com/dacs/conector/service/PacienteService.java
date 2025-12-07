package com.dacs.conector.service;

import com.dacs.conector.dto.PacienteDto;

public interface PacienteService {


    PacienteDto getPacientes(int cantidad, String nacionalidad);

}
