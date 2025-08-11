package br.com.ysla.buscacep.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "consulta_cep",
       indexes = {
           @Index(name = "idx_consulta_cep_cep", columnList = "cep"),
           @Index(name = "idx_consulta_cep_data", columnList = "data_consulta")
       })
public class ConsultaCep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 9, nullable = false)
    private String cep;

    @Column(length = 255)
    private String logradouro;

    @Column(length = 120)
    private String bairro;

    @Column(length = 120)
    private String localidade;

    @Column(length = 2)
    private String uf;

    @Column(name = "data_consulta", nullable = false, updatable = false)
    private LocalDateTime dataConsulta;

    @PrePersist
    void prePersist() {
        if (dataConsulta == null) dataConsulta = LocalDateTime.now();
        if (cep != null) cep = cep.trim();
    }
}
