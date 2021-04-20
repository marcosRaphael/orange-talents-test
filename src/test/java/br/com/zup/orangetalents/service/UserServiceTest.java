package br.com.zup.orangetalents.service;

import br.com.zup.orangetalents.Exception.NotUniqueException;
import br.com.zup.orangetalents.domain.Address;
import br.com.zup.orangetalents.domain.User;
import br.com.zup.orangetalents.dto.AddressDTO;
import br.com.zup.orangetalents.dto.UserDTO;
import br.com.zup.orangetalents.dto.UserWithAdressesDTO;
import br.com.zup.orangetalents.repository.AddressRepository;
import br.com.zup.orangetalents.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import util.AddressCreator;
import util.UserCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {

       User user =  UserCreator.createValidUser();

       Optional<User> optionalUser =  Optional.of(user);

       BDDMockito.when(userRepositoryMock.save(ArgumentMatchers.any(User.class)))
                .thenReturn(user);

       BDDMockito.when(userRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(optionalUser);

       BDDMockito.when(userRepositoryMock.existsBycPF(ArgumentMatchers.anyString()))
               .thenReturn(false);

        BDDMockito.when(userRepositoryMock.existsByEmail(ArgumentMatchers.anyString()))
                .thenReturn(false);
    }


    @Test
    @DisplayName("Save returns user when successful")
    void save_returnsUser_WhenSuccessful() {

        User expectedUser = UserCreator.createValidUser();
        Long userId = userService.save(UserDTO.fromUser(UserCreator.createValidUser()));

        Assertions.assertEquals(userId, expectedUser.getId());
    }

    @Test
    @DisplayName("Save returns NotUniqueException when the cpf is not unique")
    void save_returnsNotUniqueException_whenTheCPFIsNotUnique() {

        BDDMockito.when(userRepositoryMock.existsBycPF(ArgumentMatchers.anyString()))
                .thenReturn(true);

        Assertions.assertThrows(NotUniqueException.class
                , () -> userService.save(UserDTO.fromUser(UserCreator.createValidUser())));
    }

    @Test
    @DisplayName("Save returns NotUniqueException when the email is not unique")
    void save_returnsNotUniqueException_whenTheEmailIsNotUnique() {

        BDDMockito.when(userRepositoryMock.existsByEmail(ArgumentMatchers.anyString()))
                .thenReturn(true);

        Assertions.assertThrows(NotUniqueException.class
                , () -> userService.save(UserDTO.fromUser(UserCreator.createValidUser())));
    }

    @Test
    @DisplayName("FindById returns user when successful")
    void findById_returnsUser_whenSuccessful() {

        User expectedUser = UserCreator.createValidUser();
        User user = userService.findById(1L);

        Assertions.assertEquals(user, expectedUser);
    }
}