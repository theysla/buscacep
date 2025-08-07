package br.com.ysla.buscacep.service;

import br.com.ysla.buscacep.client.ViaCepClient;
import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.repository.ConsultaCepRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaCepServiceTest {

    @Mock
    private ViaCepClient viaCepClient;

    @Mock
    private ConsultaCepRepository repository;

    @InjectMocks
    private ConsultaCepService service;

    @Test
    void deveBuscarCepESalvarConsulta() {
        String cep = "01001000";
        ConsultaCep consultaMock = new ConsultaCep();
        consultaMock.setCep(cep);

        when(viaCepClient.buscarCep(cep)).thenReturn(consultaMock);
        when(repository.save(any(ConsultaCep.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ConsultaCep resultado = service.buscarCep(cep);

        assertEquals(cep, resultado.getCep());
        verify(viaCepClient).buscarCep(cep);
        verify(repository).save(any(ConsultaCep.class));
    }
}