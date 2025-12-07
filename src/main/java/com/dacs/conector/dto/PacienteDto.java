package com.dacs.conector.dto;

import java.util.List;

import lombok.Data;

@Data
public class PacienteDto {
    private List<Result> results;

    @Data
    public static class Result {
        private Name name;
        private Location location;
        private String email;
        private Dob dob;
        private String phone;
        private String cell;
        private Id id;

        // getters y setters
    }

    @Data
    public static class Name {
        private String first;
        private String last;
        // getters y setters
    }

    @Data
    public static class Location {
        private String city;
        private String state;
        // getters y setters
    }

    @Data
    public static class Dob {
        private String date;
        // getters y setters
    }
    @Data
    public static class Id {
        private String value;
        // getters y setters
    }
}
