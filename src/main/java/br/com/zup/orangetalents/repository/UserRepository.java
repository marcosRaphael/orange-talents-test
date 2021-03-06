package br.com.zup.orangetalents.repository;

import br.com.zup.orangetalents.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsBycPF(String cPF);
}
