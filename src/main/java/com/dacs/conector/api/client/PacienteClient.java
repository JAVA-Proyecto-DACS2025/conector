package com.dacs.conector.api.client;

import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dacs.conector.dto.PacienteDto;

@FeignClient(name = "PacienteClient", url = "https://randomuser.me")

public interface PacienteClient {

    @GetMapping("/api/")
    PacienteDto search(
            @RequestParam("results") int results,
            @RequestParam("nat") String nat);
}
