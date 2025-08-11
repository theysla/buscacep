package br.com.ysla.buscacep.controller;

import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.service.ConsultaCepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final ConsultaCepService consultaCepService;

    public CepController(ConsultaCepService consultaCepService) {
        this.consultaCepService = consultaCepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<ConsultaCep> consultarCep(@PathVariable String cep) {
        String cepNormalizado = cep == null ? "" : cep.replaceAll("\\D", "");
        if (cepNormalizado.length() != 8) {
            return ResponseEntity.badRequest().build();
        }

        ConsultaCep resultado = consultaCepService.buscarCep(cepNormalizado);
        if (resultado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado);
    }
}
