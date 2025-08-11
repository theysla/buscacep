package br.com.ysla.buscacep.service;

import br.com.ysla.buscacep.client.ViaCepClient;
import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.repository.ConsultaCepRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ConsultaCepService {

    private static final Logger log = LoggerFactory.getLogger(ConsultaCepService.class);

    private final ViaCepClient viaCepClient;
    private final ConsultaCepRepository repository;

    public ConsultaCepService(ViaCepClient viaCepClient, ConsultaCepRepository repository) {
        this.viaCepClient = viaCepClient;
        this.repository = repository;
    }

    @Transactional
    public ConsultaCep buscarCep(String cep) {
        String cepNormalizado = normalizaCep(cep);
        validaCep(cepNormalizado);

        ConsultaCep consulta = viaCepClient.buscarCep(cepNormalizado);
        if (consulta == null) {
            throw new IllegalStateException("Resposta vazia da API ViaCEP");
        }

        consulta.setCep(cepNormalizado);
        consulta.setDataConsulta(LocalDateTime.now());

        ConsultaCep salvo = repository.save(consulta);
        log.info("Consulta de CEP {} persistida (id={})", salvo.getCep(), salvo.getId());
        return salvo;
    }

    private String normalizaCep(String cep) {
        return cep == null ? "" : cep.replaceAll("\\D", "");
    }

    private void validaCep(String cep) {
        if (cep.length() != 8) {
            throw new IllegalArgumentException("CEP inválido: deve conter 8 dígitos");
        }
    }

    private String formataCep(String cep) {
        return cep.substring(0, 5) + "-" + cep.substring(5);
    }
}
