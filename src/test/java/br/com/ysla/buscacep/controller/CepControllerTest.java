package br.com.ysla.buscacep.controller;

import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.service.CepService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CepControllerTest {

    @Mock
    private CepService cepService;

    @InjectMocks
    private CepController controller;

    @Test
    void deveChamarServiceParaBuscarCep() {
        String cep = "01001000";
        ConsultaCep retornoMockado = new ConsultaCep();
        retornoMockado.setUf("SP");
        retornoMockado.setLocalidade("São Paulo");

        when(cepService.buscarCep(cep)).thenReturn(retornoMockado);

        ConsultaCep result = controller.buscarCep(cep);

        assertEquals("SP", result.getUf());
        assertEquals("São Paulo", result.getLocalidade());
        verify(cepService).buscarCep(cep);
    }
}