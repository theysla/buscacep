package br.com.ysla.buscacep.model;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class CepResponseTest {

    @Test
    void deveDesserializarRespostaDoViaCep() throws Exception {
        String json = """
                  {
                    "cep":"01001-000",
                    "logradouro":"Praça da Sé",
                    "complemento":"lado ímpar",
                    "bairro":"Sé",
                    "localidade":"São Paulo",
                    "uf":"SP",
                    "ibge":"3550308",
                    "gia":"1004",
                    "ddd":"11",
                    "siafi":"7107"
                  }
                """;

        CepResponse r = new ObjectMapper().readValue(json, CepResponse.class);

        assertAll(
                () -> assertEquals("01001-000", r.getCep()), // ViaCEP vem formatado
                () -> assertEquals("Praça da Sé", r.getLogradouro()),
                () -> assertEquals("lado ímpar", r.getComplemento()),
                () -> assertEquals("Sé", r.getBairro()),
                () -> assertEquals("São Paulo", r.getLocalidade()),
                () -> assertEquals("SP", r.getUf()),
                () -> assertEquals("3550308", r.getIbge()),
                () -> assertEquals("1004", r.getGia()),
                () -> assertEquals("11", r.getDdd()),
                () -> assertEquals("7107", r.getSiafi()));
    }
}