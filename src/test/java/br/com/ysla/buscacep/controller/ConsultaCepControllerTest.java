package br.com.ysla.buscacep.controller;

import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.service.ConsultaCepService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsultaCepControllerTest {

    @Mock
    private ConsultaCepService consultaCepService;

    @InjectMocks
    private ConsultaCepController controller;

    @Test
    void deveChamarServiceParaBuscarCep() {
        String cep = "01001000";
        ConsultaCep retornoMockado = new ConsultaCep();
        retornoMockado.setUf("SP");
        retornoMockado.setLocalidade("São Paulo");

        when(consultaCepService.buscarCep(cep)).thenReturn(retornoMockado);

        ResponseEntity<ConsultaCep> result = controller.consultar(cep);

        assertEquals("SP", result.getBody().getUf());
        assertEquals("São Paulo", result.getBody().getLocalidade());
        verify(consultaCepService).buscarCep(cep);
    }
}