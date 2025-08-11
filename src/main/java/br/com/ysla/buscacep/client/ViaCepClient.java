package br.com.ysla.buscacep.client;

import br.com.ysla.buscacep.model.ConsultaCep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

    @GetMapping(value = "/{cep}/json", produces = "application/json")
    ConsultaCep buscarCep(@PathVariable("cep") String cep);
}
