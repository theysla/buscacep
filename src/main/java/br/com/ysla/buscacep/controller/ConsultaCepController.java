package br.com.ysla.buscacep.controller;

import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.service.ConsultaCepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaCepController {

    private final ConsultaCepService consultaCepService;

    public ConsultaCepController(ConsultaCepService consultaCepService) {
        this.consultaCepService = consultaCepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<ConsultaCep> consultar(@PathVariable String cep) {
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
