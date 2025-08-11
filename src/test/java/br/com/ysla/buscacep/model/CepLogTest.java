package br.com.ysla.buscacep.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CepLogTest {

    @Test
    @DisplayName("Deve setar e obter todos os campos corretamente")
    void deveSetarEObterCampos() {
        LocalDateTime instanteFixo = LocalDateTime.of(2025, 8, 11, 10, 0, 0);

        CepLog log = new CepLog();
        log.setId(1L);
        log.setCep("01001000");
        log.setLogradouro("Praça da Sé");
        log.setBairro("Sé");
        log.setLocalidade("São Paulo");
        log.setUf("SP");
        log.setDataConsulta(instanteFixo);
        log.setResultado("OK");

        assertAll(
                () -> assertEquals(1L, log.getId()),
                () -> assertEquals("01001000", log.getCep()),
                () -> assertEquals("Praça da Sé", log.getLogradouro()),
                () -> assertEquals("Sé", log.getBairro()),
                () -> assertEquals("São Paulo", log.getLocalidade()),
                () -> assertEquals("SP", log.getUf()),
                () -> assertEquals(instanteFixo, log.getDataConsulta()),
                () -> assertEquals("OK", log.getResultado()));
    }

    @Test
    @DisplayName("equals/hashCode com @Data: todos os campos contam")
    void equalsHashCode() {
        CepLog a = new CepLog();
        a.setId(10L);
        a.setCep("01001000");

        CepLog b = new CepLog();
        b.setId(10L);
        b.setCep("01001000");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        CepLog c = new CepLog();
        c.setId(10L);
        c.setCep("99999999");

        assertNotEquals(a, c);
    }

    @Test
    @DisplayName("toString deve conter campos principais (Lombok @Data)")
    void toStringDeveConterCamposPrincipais() {
        CepLog log = new CepLog();
        log.setId(1L);
        log.setCep("01001000");
        String s = log.toString();

        assertTrue(s.contains("id=1"));
        assertTrue(s.contains("cep=01001000"));
    }
}
