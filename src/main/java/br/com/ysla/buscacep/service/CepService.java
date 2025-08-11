package br.com.ysla.buscacep.service;

import br.com.ysla.buscacep.client.ViaCepClient;
import br.com.ysla.buscacep.model.CepLog;
import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.repository.CepLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CepService {

    private static final Logger log = LoggerFactory.getLogger(CepService.class);

    private final ViaCepClient viaCepClient;
    private final CepLogRepository cepLogRepository;

    public CepService(ViaCepClient viaCepClient, CepLogRepository cepLogRepository) {
        this.viaCepClient = viaCepClient;
        this.cepLogRepository = cepLogRepository;
    }

    @Transactional
    public ConsultaCep buscarCep(String cep) {
        String cepNormalizado = normalizaCep(cep);
        validaCep(cepNormalizado);

        try {
            ConsultaCep resposta = viaCepClient.buscarCep(cepNormalizado);

            CepLog logEntry = new CepLog();
            logEntry.setCep(resposta.getCep());
            logEntry.setLogradouro(resposta.getLogradouro());
            logEntry.setBairro(resposta.getBairro());
            logEntry.setLocalidade(resposta.getLocalidade());
            logEntry.setUf(resposta.getUf());
            logEntry.setDataConsulta(LocalDateTime.now());
            logEntry.setResultado("OK");
            cepLogRepository.save(logEntry);

            log.info("Consulta de CEP {} realizada com sucesso", cepNormalizado);
            return resposta;

        } catch (Exception e) {
            CepLog logEntry = new CepLog();
            logEntry.setCep(cepNormalizado);
            logEntry.setDataConsulta(LocalDateTime.now());
            logEntry.setResultado("ERRO: " + e.getMessage());
            cepLogRepository.save(logEntry);

            log.error("Falha ao consultar CEP {}: {}", cepNormalizado, e.getMessage());
            throw e; 
        }
    }

    private String normalizaCep(String cep) {
        return cep == null ? "" : cep.replaceAll("\\D", "");
    }

    private void validaCep(String cep) {
        if (cep.length() != 8) {
            throw new IllegalArgumentException("CEP inválido: deve conter 8 dígitos");
        }
    }
}
