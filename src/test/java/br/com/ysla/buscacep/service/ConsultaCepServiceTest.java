package br.com.ysla.buscacep.service;

import br.com.ysla.buscacep.client.ViaCepClient;
import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.repository.ConsultaCepRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaCepServiceTest {

    @Mock
    ViaCepClient viaCepClient;

    @Mock
    ConsultaCepRepository repository;

    @InjectMocks
    ConsultaCepService service;

    @Test
    void deveBuscarCepNormalizarESalvar() {
        String cepEntrada = "01001-000";
        String cepEsperado = "01001000";

        ConsultaCep consultaMock = new ConsultaCep();
        consultaMock.setCep(cepEsperado);

        when(viaCepClient.buscarCep("01001000")).thenReturn(consultaMock);
        when(repository.save(any(ConsultaCep.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        ConsultaCep resultado = service.buscarCep(cepEntrada);

        assertNotNull(resultado);
        assertEquals(cepEsperado, resultado.getCep(), "CEP deve vir sem formatação (8 dígitos)");
        assertNotNull(resultado.getDataConsulta(), "dataConsulta deve ser preenchida");

        verify(viaCepClient, times(1)).buscarCep("01001000");

        ArgumentCaptor<ConsultaCep> captor = ArgumentCaptor.forClass(ConsultaCep.class);
        verify(repository, times(1)).save(captor.capture());
        verifyNoMoreInteractions(repository);

        ConsultaCep salvo = captor.getValue();
        assertEquals(cepEsperado, salvo.getCep());
        assertNotNull(salvo.getDataConsulta());
    }

    @Test
    void deveLancarExcecaoQuandoCepInvalido() {
        assertThrows(IllegalArgumentException.class, () -> service.buscarCep("123"),
                "CEP inválido deve lançar IllegalArgumentException");
        verifyNoInteractions(viaCepClient, repository);
    }
}
