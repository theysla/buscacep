package br.com.ysla.buscacep.repository;

import br.com.ysla.buscacep.model.CepLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CepLogRepositoryTest {

    @Autowired
    private CepLogRepository repository;

    @Test
    void deveSalvarEBuscarCepLog() {
        LocalDateTime instanteFixo = LocalDateTime.of(2025, 8, 11, 1, 0, 0);

        CepLog log = new CepLog();
        log.setCep("01001000");
        log.setLogradouro("Praça da Sé");
        log.setBairro("Sé");
        log.setLocalidade("São Paulo");
        log.setUf("SP");
        log.setDataConsulta(instanteFixo);
        log.setResultado("OK");

        CepLog salvo = repository.save(log);

        Optional<CepLog> encontrado = repository.findById(salvo.getId());
        assertTrue(encontrado.isPresent(), "registro não encontrado após salvar");

        CepLog e = encontrado.get();
        assertAll(
            () -> assertNotNull(salvo.getId(), "id não foi gerado"),
            () -> assertEquals("01001000", e.getCep()),
            () -> assertEquals("Praça da Sé", e.getLogradouro()),
            () -> assertEquals("Sé", e.getBairro()),
            () -> assertEquals("São Paulo", e.getLocalidade()),
            () -> assertEquals("SP", e.getUf()),
            () -> assertEquals(instanteFixo, e.getDataConsulta()),
            () -> assertEquals("OK", e.getResultado())
        );
    }
}
