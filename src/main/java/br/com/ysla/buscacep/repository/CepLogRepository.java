package br.com.ysla.buscacep.repository;

import br.com.ysla.buscacep.model.CepLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CepLogRepository extends JpaRepository<CepLog, Long> {
}
