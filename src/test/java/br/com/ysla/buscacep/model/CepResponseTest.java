package br.com.ysla.buscacep.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CepResponseTest {

    @Test
    void gettersAndSetters() {
        CepResponse response = new CepResponse();
        response.setCep("01001000");
        response.setLogradouro("Praça da Sé");
        response.setComplemento("lado ímpar");
        response.setBairro("Sé");
        response.setLocalidade("São Paulo");
        response.setUf("SP");
        response.setIbge("3550308");
        response.setGia("1004");
        response.setDdd("11");
        response.setSiafi("7107");

        assertEquals("01001000", response.getCep());
        assertEquals("Praça da Sé", response.getLogradouro());
        assertEquals("lado ímpar", response.getComplemento());
        assertEquals("Sé", response.getBairro());
        assertEquals("São Paulo", response.getLocalidade());
        assertEquals("SP", response.getUf());
        assertEquals("3550308", response.getIbge());
        assertEquals("1004", response.getGia());
        assertEquals("11", response.getDdd());
        assertEquals("7107", response.getSiafi());
    }
}