package br.com.ysla.buscacep.controller;

import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.service.ConsultaCepService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ConsultaCepControllerTest {

    @Mock
    private ConsultaCepService consultaCepService;

    @InjectMocks
    private ConsultaCepController controller;

    @Test
    @DisplayName("Deve delegar ao service e retornar 200 com os dados do CEP")
    void deveChamarServiceParaBuscarCep() {
        // Arrange
        String cep = "01001000";
        ConsultaCep retornoMockado = new ConsultaCep();
        retornoMockado.setUf("SP");
        retornoMockado.setLocalidade("São Paulo");

        given(consultaCepService.buscarCep(cep)).willReturn(retornoMockado);

        // Act
        ResponseEntity<ConsultaCep> result = controller.consultar(cep);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("SP", result.getBody().getUf());
        assertEquals("São Paulo", result.getBody().getLocalidade());

        verify(consultaCepService).buscarCep(cep);
        verifyNoMoreInteractions(consultaCepService);
    }
}
