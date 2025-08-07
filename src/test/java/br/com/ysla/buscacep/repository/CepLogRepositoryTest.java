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
        CepLog log = new CepLog();
        log.setCep("01001000");
        log.setLogradouro("Praça da Sé");
        log.setBairro("Sé");
        log.setLocalidade("São Paulo");
        log.setUf("SP");
        log.setDataConsulta(LocalDateTime.now());

        CepLog salvo = repository.save(log);

        Optional<CepLog> encontrado = repository.findById(salvo.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("01001000", encontrado.get().getCep());
        assertEquals("São Paulo", encontrado.get().getLocalidade());
        assertEquals("SP", encontrado.get().getUf());
    }
}    