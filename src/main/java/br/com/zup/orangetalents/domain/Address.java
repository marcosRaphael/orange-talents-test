package br.com.zup.orangetalents.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private User user;

    @NotBlank(message = "É necessário inserir um logradouro.")
    private String logradouro;

    @NotBlank(message = "É necessário inserir um numero.")
    private String numero;

    private String complemento;

    @NotBlank(message = "É necessário inserir um bairro.")
    private String bairro;

    @NotBlank(message = "É necessário inserir uma cidade.")
    private String cidade;

    @NotBlank(message = "É necessário inserir um estado.")
    private String estado;

    @NotBlank(message = "É necessário inserir um cep.")
    private String cep;

    public Address() {}

    public Address(Long id, User user, String logradouro, String numero, String complemento, String bairro, String cidade
    ,String estado, String cep) {
        this.id = id;
        this.user = user;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(user, address.user)
                && Objects.equals(logradouro, address.logradouro)
                && Objects.equals(numero, address.numero)
                && Objects.equals(complemento, address.complemento)
                && Objects.equals(bairro, address.bairro)
                && Objects.equals(cidade, address.cidade)
                && Objects.equals(estado, address.estado)
                && Objects.equals(cep, address.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, logradouro, numero, complemento, bairro, cidade, estado, cep);
    }
}
