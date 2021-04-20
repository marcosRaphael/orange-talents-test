package br.com.zup.orangetalents.service;

import br.com.zup.orangetalents.domain.Address;
import br.com.zup.orangetalents.domain.User;
import br.com.zup.orangetalents.dto.AddressDTO;
import br.com.zup.orangetalents.dto.UserWithAdressesDTO;
import br.com.zup.orangetalents.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    public AddressService(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    public Address save(AddressDTO addressDTO, Long userId) {

        User user = userService.findById(userId);

        Address address =  addressDTO.toAddress();
        address.setUser(user);

        return addressRepository.save(address);
    }

    public UserWithAdressesDTO findAllByUserId(Long userId) {
        User user = userService.findById(userId);

        List<Address> addressList = addressRepository.findByUserId(userId);

        UserWithAdressesDTO userWithAdressesDTO = new UserWithAdressesDTO();

        userWithAdressesDTO.setName(user.getName());
        userWithAdressesDTO.setcPF(user.getcPF());
        userWithAdressesDTO.setEmail(user.getEmail());
        userWithAdressesDTO.setBirthDate(user.getBirthDate());
        userWithAdressesDTO.setAddressDTO(UserWithAdressesDTO.addressListToAddressDTOList(addressList));

        return userWithAdressesDTO;

    }
}
