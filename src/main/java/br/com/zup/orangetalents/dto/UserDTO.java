package br.com.zup.orangetalents.dto;

import br.com.zup.orangetalents.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class UserDTO implements Serializable {


    @NotBlank(message = "É necessário inserir um nome.")
    private String name;

    @Column(unique = true)
    @NotEmpty(message = "É necessário inserir um email.")
    @Email(message = "É necessário inserir um email válido.")
    private String email;

    @Column(unique = true)
    @NotEmpty(message = "É necessário inserir um CPF.")
    @CPF(message = "É necessário inserir um CPF válido.")
    private String cPF;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "É necessário inserir uma data de nascimento.")
    @Schema(implementation = String.class, example = "dd/MM/yyyy")
    private LocalDate birthDate;

    public UserDTO() {}

    public UserDTO(String name, String email, String cPf, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.cPF = cPf;
        this.birthDate = birthDate;
    }

    public User toUser() {
        return new User(null, name, email, cPF, birthDate);
    }

    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getName(), user.getEmail(), user.getcPF(), user.getBirthDate());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getcPF() {
        return cPF;
    }

    public void setcPF(String cPF) {
        this.cPF = cPF;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(name, userDTO.name) && Objects.equals(email, userDTO.email) && Objects.equals(cPF, userDTO.cPF) && Objects.equals(birthDate, userDTO.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, cPF, birthDate);
    }
}
