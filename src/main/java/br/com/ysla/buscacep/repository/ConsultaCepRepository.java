package br.com.ysla.buscacep.repository;

import br.com.ysla.buscacep.model.ConsultaCep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaCepRepository extends JpaRepository<ConsultaCep, Long> {
}
