package br.com.ysla.buscacep.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ConsultaCepTest {

    @Test
    void gettersAndSetters() {
        ConsultaCep c = new ConsultaCep();
        c.setId(1L);
        c.setCep("01001000");
        c.setLogradouro("Praça da Sé");
        c.setBairro("Sé");
        c.setLocalidade("São Paulo");
        c.setUf("SP");
        var now = LocalDateTime.now();
        c.setDataConsulta(now);

        assertAll(
                () -> assertEquals(1L, c.getId()),
                () -> assertEquals("01001000", c.getCep()),
                () -> assertEquals("Praça da Sé", c.getLogradouro()),
                () -> assertEquals("Sé", c.getBairro()),
                () -> assertEquals("São Paulo", c.getLocalidade()),
                () -> assertEquals("SP", c.getUf()),
                () -> assertEquals(now, c.getDataConsulta()));
    }
}
