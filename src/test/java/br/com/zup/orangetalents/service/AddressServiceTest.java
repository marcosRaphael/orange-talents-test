package br.com.zup.orangetalents.service;

import br.com.zup.orangetalents.domain.Address;
import br.com.zup.orangetalents.domain.User;
import br.com.zup.orangetalents.dto.AddressDTO;
import br.com.zup.orangetalents.dto.UserWithAdressesDTO;
import br.com.zup.orangetalents.repository.AddressRepository;
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
import org.springframework.util.Assert;
import util.AddressCreator;
import util.UserCreator;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepositoryMock;

    @Mock
    private UserService userServiceMock;

    @BeforeEach
    void setUp() {

        User user = UserCreator.createValidUser();
        Address address = AddressCreator.createValidAddress();

        List<Address> addressList = new ArrayList<>();

        addressList.add(address);

        BDDMockito.when(userServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(user);

        BDDMockito.when(addressRepositoryMock.save(ArgumentMatchers.any(Address.class)))
                .thenReturn(address);

        BDDMockito.when(addressRepositoryMock.findByUserId(ArgumentMatchers.anyLong()))
                .thenReturn(addressList);

    }

    @Test
    @DisplayName("Save returns address when successful")
    void save_returnsAddress_WhenSuccessful() {

        Address expectedAddress = AddressCreator.createValidAddress();
        Address address = addressService.save(AddressDTO.fromAddress(AddressCreator.createValidAddress()), 1L);

        Assertions.assertEquals(address, expectedAddress);
    }

    @Test
    @DisplayName("FindAllByUserId returns user and address when successful")
    void findAllByUserId_returnsUserAndAddress_whenSuccessful() {

        User expectedUser = UserCreator.createValidUser();
        Address expectedAddress = AddressCreator.createValidAddress();

        UserWithAdressesDTO expectedUserWithAddressDTO = new UserWithAdressesDTO();

        List<Address> expectedAddressList = new ArrayList<>();

        expectedAddressList.add(expectedAddress);

        expectedUserWithAddressDTO.setName(expectedUser.getName());
        expectedUserWithAddressDTO.setcPF(expectedUser.getcPF());
        expectedUserWithAddressDTO.setEmail(expectedUser.getEmail());
        expectedUserWithAddressDTO.setBirthDate(expectedUser.getBirthDate());
        expectedUserWithAddressDTO.setAddressDTO(UserWithAdressesDTO.addressListToAddressDTOList(expectedAddressList));

        UserWithAdressesDTO userWithAdressesDTO = addressService.findAllByUserId(1L);

        Assertions.assertEquals(userWithAdressesDTO, expectedUserWithAddressDTO);
    }


}