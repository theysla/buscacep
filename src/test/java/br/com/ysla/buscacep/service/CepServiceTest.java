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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CepServiceTest {

    @Mock
    ViaCepClient viaCepClient;

    @Mock
    CepLogRepository cepLogRepository;

    @InjectMocks
    CepService cepService;

    @Test
    void deveBuscarCepESalvarLog() {
        String cep = "01001000";
        ConsultaCep consultaMock = new ConsultaCep();
        consultaMock.setCep(cep);

        when(viaCepClient.buscarCep(cep)).thenReturn(consultaMock);

        ConsultaCep resultado = cepService.buscarCep(cep);

        assertNotNull(resultado);
        assertEquals(cep, resultado.getCep());
        verify(viaCepClient, times(1)).buscarCep(cep);

        ArgumentCaptor<CepLog> logCaptor = ArgumentCaptor.forClass(CepLog.class);
        verify(cepLogRepository, times(1)).save(logCaptor.capture());
        verifyNoMoreInteractions(cepLogRepository);

        CepLog logSalvo = logCaptor.getValue();
        assertAll(
            () -> assertNotNull(logSalvo, "Log não capturado"),
            () -> assertEquals(cep, logSalvo.getCep(), "CEP do log divergente"),
            () -> assertNotNull(logSalvo.getDataConsulta(), "dataConsulta não preenchida"),
            () -> assertNotNull(logSalvo.getResultado(), "resultado não preenchido"),
            () -> assertFalse(logSalvo.getResultado().isBlank(), "resultado em branco")
        );
    }
}
