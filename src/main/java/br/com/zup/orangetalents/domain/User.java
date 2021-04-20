package br.com.zup.orangetalents.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.br.CPF;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private final List<Address> adresses = new ArrayList<>();

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

    @NotNull(message = "É necessário inserir uma data de nascimento.")
    private LocalDate birthDate;

    public User() {}

    public User(Long id, String name, String email, String cPF, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cPF = cPF;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Address> getAdresses() {
        return adresses;
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
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(adresses, user.adresses)
                && Objects.equals(name, user.name)
                && Objects.equals(email, user.email)
                && Objects.equals(cPF, user.cPF)
                && Objects.equals(birthDate, user.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adresses, name, email, cPF, birthDate);
    }
}
