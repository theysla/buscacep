package br.com.ysla.buscacep.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ConsultaCepTest {

    @Test
    void gettersAndSetters() {
        ConsultaCep consulta = new ConsultaCep();
        consulta.setId(1L);
        consulta.setCep("01001000");
        consulta.setLogradouro("Praça da Sé");
        consulta.setBairro("Sé");
        consulta.setLocalidade("São Paulo");
        consulta.setUf("SP");
        LocalDateTime now = LocalDateTime.now();
        consulta.setDataConsulta(now);

        assertEquals(1L, consulta.getId());
        assertEquals("01001000", consulta.getCep());
        assertEquals("Praça da Sé", consulta.getLogradouro());
        assertEquals("Sé", consulta.getBairro());
        assertEquals("São Paulo", consulta.getLocalidade());
        assertEquals("SP", consulta.getUf());
        assertEquals(now, consulta.getDataConsulta());
    }       
}
