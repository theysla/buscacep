package br.com.ysla.buscacep.client;

import br.com.ysla.buscacep.model.ConsultaCep;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ViaCepClientTest {

    @MockBean
    private ViaCepClient viaCepClient;

    @Test
    void deveRetornarConsultaCepMockado() {
        ConsultaCep mockCep = new ConsultaCep();
        mockCep.setCep("01001000");
        mockCep.setUf("SP");
        mockCep.setLocalidade("São Paulo");

        when(viaCepClient.buscarCep("01001000")).thenReturn(mockCep);

        ConsultaCep resultado = viaCepClient.buscarCep("01001000");

        assertEquals("SP", resultado.getUf());
        assertEquals("São Paulo", resultado.getLocalidade());
        assertEquals("01001000", resultado.getCep());
    }
}