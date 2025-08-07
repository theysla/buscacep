package br.com.ysla.buscacep.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CepLogTest {

    @Test
    void gettersAndSetters() {
        CepLog log = new CepLog();
        log.setId(1L);
        log.setCep("01001000");
        log.setLogradouro("Praça da Sé");
        log.setBairro("Sé");
        log.setLocalidade("São Paulo");
        log.setUf("SP");
        LocalDateTime now = LocalDateTime.now();
        log.setDataConsulta(now);

        assertEquals(1L, log.getId());
        assertEquals("01001000", log.getCep());
        assertEquals("Praça da Sé", log.getLogradouro());
        assertEquals("Sé", log.getBairro());
        assertEquals("São Paulo", log.getLocalidade());
        assertEquals("SP", log.getUf());
        assertEquals(now, log.getDataConsulta());
    }
}