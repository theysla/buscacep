package br.com.ysla.buscacep.service;

import br.com.ysla.buscacep.client.ViaCepClient;
import br.com.ysla.buscacep.model.CepLog;
import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.repository.CepLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CepService {

    private final ViaCepClient viaCepClient;
    private final CepLogRepository cepLogRepository;

    public CepService(ViaCepClient viaCepClient, CepLogRepository cepLogRepository) {
        this.viaCepClient = viaCepClient;
        this.cepLogRepository = cepLogRepository;
    }

    public ConsultaCep buscarCep(String cep) {
        ConsultaCep response = viaCepClient.buscarCep(cep);

        // Salvar log
        CepLog log = new CepLog();
        log.setCep(cep);
        log.setDataConsulta(LocalDateTime.now());
        log.setResultado(response.toString()); 
        cepLogRepository.save(log);

        return response;
    }
}
