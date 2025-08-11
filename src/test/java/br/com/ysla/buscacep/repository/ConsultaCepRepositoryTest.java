package br.com.ysla.buscacep.repository;

import br.com.ysla.buscacep.model.ConsultaCep;
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
class ConsultaCepRepositoryTest {

    @Autowired
    private ConsultaCepRepository repository;

    @Test
    void deveSalvarEBuscarConsultaCep() {
        LocalDateTime instanteFixo = LocalDateTime.of(2025, 8, 11, 1, 0, 0);

        ConsultaCep consulta = new ConsultaCep();
        consulta.setCep("01001000");
        consulta.setUf("SP");
        consulta.setLocalidade("São Paulo");
        consulta.setDataConsulta(instanteFixo);

        ConsultaCep salvo = repository.save(consulta);

        Optional<ConsultaCep> encontrado = repository.findById(salvo.getId());
        assertTrue(encontrado.isPresent(), "registro não encontrado");

        ConsultaCep e = encontrado.get();
        assertAll(
            () -> assertNotNull(salvo.getId(), "id não foi gerado"),
            () -> assertEquals("01001000", e.getCep()),
            () -> assertEquals("SP", e.getUf()),
            () -> assertEquals("São Paulo", e.getLocalidade()),
            () -> assertEquals(instanteFixo, e.getDataConsulta())
        );
    }
}
