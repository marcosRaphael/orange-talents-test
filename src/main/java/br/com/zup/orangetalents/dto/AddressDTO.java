package br.com.zup.orangetalents.dto;

import br.com.zup.orangetalents.domain.Address;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class AddressDTO {

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

    public AddressDTO() {}

    public AddressDTO(String logradouro, String numero, String complemento
            , String bairro, String cidade, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Address toAddress() {
        return new Address(null, null, logradouro, numero, complemento, bairro, cidade, estado, cep);
    }

    public static  AddressDTO fromAddress(Address address) {
        return new AddressDTO(address.getLogradouro(), address.getNumero(), address.getComplemento()
        , address.getBairro(), address.getCidade(), address.getEstado(), address.getCep());
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
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(logradouro, that.logradouro) && Objects.equals(numero, that.numero) && Objects.equals(complemento, that.complemento) && Objects.equals(bairro, that.bairro) && Objects.equals(cidade, that.cidade) && Objects.equals(estado, that.estado) && Objects.equals(cep, that.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logradouro, numero, complemento, bairro, cidade, estado, cep);
    }
}
