package br.com.ysla.buscacep.service;

import br.com.ysla.buscacep.client.ViaCepClient;
import br.com.ysla.buscacep.model.CepLog;
import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.repository.CepLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CepServiceTest {

    @Mock
    private ViaCepClient viaCepClient;

    @Mock
    private CepLogRepository cepLogRepository;

    @InjectMocks
    private CepService cepService;

    @Test
    void deveBuscarCepESalvarLog() {
        String cep = "01001000";
        ConsultaCep consultaMock = new ConsultaCep();
        consultaMock.setCep(cep);

        when(viaCepClient.buscarCep(cep)).thenReturn(consultaMock);

        ConsultaCep resultado = cepService.buscarCep(cep);

        assertEquals(cep, resultado.getCep());
        verify(viaCepClient).buscarCep(cep);

        ArgumentCaptor<CepLog> logCaptor = ArgumentCaptor.forClass(CepLog.class);
        verify(cepLogRepository).save(logCaptor.capture());
        CepLog logSalvo = logCaptor.getValue();

        assertEquals(cep, logSalvo.getCep());
        // Você pode adicionar mais asserts para os outros campos se desejar
    }
}