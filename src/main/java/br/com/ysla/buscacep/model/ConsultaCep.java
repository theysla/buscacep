package br.com.ysla.buscacep.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ConsultaCep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    @Column(name = "data_consulta") 
    private LocalDateTime dataConsulta;

    // Getters
    public Long getId() { return id; }
    public String getCep() { return cep; }
    public String getLogradouro() { return logradouro; }
    public String getBairro() { return bairro; }
    public String getLocalidade() { return localidade; }
    public String getUf() { return uf; }
    public LocalDateTime getDataConsulta() { return dataConsulta; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setCep(String cep) { this.cep = cep; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public void setLocalidade(String localidade) { this.localidade = localidade; }
    public void setUf(String uf) { this.uf = uf; }
    public void setDataConsulta(LocalDateTime dataConsulta) { this.dataConsulta = dataConsulta; }
}
