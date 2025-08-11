package br.com.ysla.buscacep.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "cep_logs")
@Data
public class CepLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8, nullable = false)
    private String cep;

    @Column(length = 255)
    private String logradouro;

    @Column(length = 100)
    private String bairro;

    @Column(length = 100)
    private String localidade;

    @Column(length = 2)
    private String uf;

    @Column(nullable = false)
    private LocalDateTime dataConsulta;

    @Column(length = 50)
    private String resultado;

    @PrePersist
    public void prePersist() {
        if (dataConsulta == null) {
            dataConsulta = LocalDateTime.now();
        }
    }
}
