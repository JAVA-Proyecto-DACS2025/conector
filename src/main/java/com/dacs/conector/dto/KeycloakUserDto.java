package com.dacs.conector.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)  // No incluir campos null en JSON
public class KeycloakUserDto {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private boolean emailVerified;
    private Long createdTimestamp;
    private Map<String, List<String>> attributes;
    
    // Campo para los roles
    private List<String> roles;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)  // No incluir campos null en JSON
    public static class Create extends KeycloakUserDto {
        private List<CredentialRepresentation> credentials;
        // No declarar roles aquí - usa el heredado del padre
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CredentialRepresentation {
        private String type = "password";
        private String value;
        private Boolean temporary = false;
    }
}