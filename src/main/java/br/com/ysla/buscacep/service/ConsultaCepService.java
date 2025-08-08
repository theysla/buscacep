package br.com.ysla.buscacep.service;

import br.com.ysla.buscacep.client.ViaCepClient;
import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.repository.ConsultaCepRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsultaCepService {

    private final ViaCepClient viaCepClient;
    private final ConsultaCepRepository repository;

    public ConsultaCepService(ViaCepClient viaCepClient, ConsultaCepRepository repository) {
        this.viaCepClient = viaCepClient;
        this.repository = repository;
    }

    public ConsultaCep buscarCep(String cep) {
    String cepLimpo = cep.trim(); 
    ConsultaCep consulta = viaCepClient.buscarCep(cepLimpo);
    consulta.setDataConsulta(LocalDateTime.now());
    return repository.save(consulta);
   }
}
