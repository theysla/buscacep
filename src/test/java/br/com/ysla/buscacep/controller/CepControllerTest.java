package br.com.ysla.buscacep.controller;

import br.com.ysla.buscacep.model.ConsultaCep;
import br.com.ysla.buscacep.service.ConsultaCepService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CepController.class)
class CepControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultaCepService consultaCepService;

    private ConsultaCep stub() {
        ConsultaCep c = new ConsultaCep();
        c.setId(1L);
        c.setCep("01001000");
        c.setLogradouro("Praça da Sé");
        c.setBairro("Sé");
        c.setLocalidade("São Paulo");
        c.setUf("SP");
        c.setDataConsulta(LocalDateTime.now());
        return c;
    }

    @Test
    void deveRetornar200_eCorpoQuandoCepValido() throws Exception {
        Mockito.when(consultaCepService.buscarCep("01001000"))
               .thenReturn(stub());

        mockMvc.perform(get("/cep/{cep}", "01001-000")
                        .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.cep", is("01001000")))
               .andExpect(jsonPath("$.logradouro", is("Praça da Sé")))
               .andExpect(jsonPath("$.localidade", is("São Paulo")))
               .andExpect(jsonPath("$.uf", is("SP")));
    }

    @Test
    void deveRetornar400QuandoCepInvalido() throws Exception {
        mockMvc.perform(get("/cep/{cep}", "123")
                        .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest());
        Mockito.verifyNoInteractions(consultaCepService);
    }

    @Test
    void deveRetornar404QuandoNaoEncontrar() throws Exception {
        Mockito.when(consultaCepService.buscarCep("99999999"))
               .thenReturn(null);

        mockMvc.perform(get("/cep/{cep}", "99999999")
                        .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }
}
