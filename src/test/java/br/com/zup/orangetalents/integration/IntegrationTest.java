package br.com.zup.orangetalents.integration;

import br.com.zup.orangetalents.domain.Address;
import br.com.zup.orangetalents.domain.User;
import br.com.zup.orangetalents.dto.AddressDTO;
import br.com.zup.orangetalents.dto.UserDTO;
import br.com.zup.orangetalents.dto.UserWithAdressesDTO;
import br.com.zup.orangetalents.repository.AddressRepository;
import br.com.zup.orangetalents.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import util.AddressCreator;
import util.UserCreator;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

class IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("UserSave returns 201 Created status when successful")
    void usersave_returns200OkStatus_WhenSuccessful() {

        User user = UserCreator.createValidUser();
        UserDTO userDTO = UserDTO.fromUser(user);

        ResponseEntity<Void> responseEntity = testRestTemplate.postForEntity("/user", userDTO, null);

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @DisplayName("UserSave returns returns 400 Bad Request status when cpf is not unique")
    void usersave_returnsreturns400BadRequest_WhenCPFIsNotUnique() {

        User user = UserCreator.createValidUser();
        UserDTO userDTO = UserDTO.fromUser(user);

        userDTO.setEmail("novoemail@email.com");

        userRepository.save(user);

        ResponseEntity<Void> responseEntity = testRestTemplate.postForEntity("/user", userDTO, null);

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("UserSave returns returns 400 Bad Request status when email is not unique")
    void usersave_returnsreturns400BadRequest_EmailIsNotUnique() {

        User user = UserCreator.createValidUser();
        UserDTO userDTO = UserDTO.fromUser(user);

        userDTO.setcPF("48461849000");

        userRepository.save(user);

        ResponseEntity<Void> responseEntity = testRestTemplate.postForEntity("/user", userDTO, null);

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("SaveUserAddress returns 201 Created status when successful")
    void saveUserAddress_returns201OkStatus_WhenSuccessful() {

        User user = UserCreator.createValidUser();

        User savedUser = userRepository.save(user);


        Address address = AddressCreator.createValidAddress();
        AddressDTO addressDTO = AddressDTO.fromAddress(address);

        ResponseEntity<Void> responseEntity = testRestTemplate
                .postForEntity("/user/" + savedUser.getId() + "/address", addressDTO, null);

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @DisplayName("SaveUserAddress returns 400 Bad Request status when user is not found")
    void saveUserAddress_returns400BadRequest_WhenUserIsNotFound() {

        Address address = AddressCreator.createValidAddress();
        AddressDTO addressDTO = AddressDTO.fromAddress(address);

        ResponseEntity<Void> responseEntity = testRestTemplate.postForEntity("/user/1/address", addressDTO, null);

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("FindUserAndAdressesById returns 200 OK status when successful")
    void findUserAndAdressesById_returns200OkStatus_WhenSuccessful() {

        User user = UserCreator.createValidUser();
        Address address = AddressCreator.createValidAddress();

        UserWithAdressesDTO userWithAddressDTO = new UserWithAdressesDTO();



        User savedUser = userRepository.save(user);
        address.setUser(savedUser);
        Address Savedaddress = addressRepository.save(address);

        userWithAddressDTO.setName(savedUser.getName());
        userWithAddressDTO.setcPF(savedUser.getcPF());
        userWithAddressDTO.setEmail(savedUser.getEmail());
        userWithAddressDTO.setBirthDate(savedUser.getBirthDate());

        List<Address> AddressList = new ArrayList<>();

        AddressList.add(Savedaddress);

        userWithAddressDTO.setAddressDTO(UserWithAdressesDTO.addressListToAddressDTOList(AddressList));

        ResponseEntity<UserWithAdressesDTO> responseEntity = testRestTemplate.getForEntity("/user/" + savedUser.getId() + "/address", UserWithAdressesDTO.class);

        Assertions.assertEquals(responseEntity.getBody(), userWithAddressDTO);

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("FindUserAndAdressesById returns 400 Bad Request status when successful")
    void findUserAndAdressesById_returns400BadRequest_WhenSuccessful() {

        ResponseEntity<UserWithAdressesDTO> responseEntity = testRestTemplate.getForEntity("/user/1/address", UserWithAdressesDTO.class);

        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

}
