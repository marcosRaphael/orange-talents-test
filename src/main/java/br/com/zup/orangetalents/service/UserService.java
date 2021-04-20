package br.com.zup.orangetalents.service;

import br.com.zup.orangetalents.Exception.NotUniqueException;
import br.com.zup.orangetalents.Exception.ResourceNotFoundException;
import br.com.zup.orangetalents.domain.User;
import br.com.zup.orangetalents.dto.UserDTO;
import br.com.zup.orangetalents.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long save(UserDTO userDTO) {

       if(userRepository.existsBycPF(userDTO.getcPF())) {
           throw new NotUniqueException("cPF", "O CPF informado já está cadastrado.");
       }

       if(userRepository.existsByEmail(userDTO.getEmail())) {
            throw new NotUniqueException("email", "O Email informado já está cadastrado.");
       }

       return  userRepository.save(userDTO.toUser()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("O usuário não foi encontrado"));
    }

}
