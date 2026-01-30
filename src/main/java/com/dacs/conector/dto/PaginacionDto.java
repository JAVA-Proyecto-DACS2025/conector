package com.dacs.conector.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PaginacionDto<T> {
    private int pagina;
    private int tamaño;


    @Getter
    @Setter
    public static class Response<T> extends PaginacionDto<T> 
    {
        private long totalElementos;
        private int totalPaginas;
        private List<T> contenido;
    }
}


