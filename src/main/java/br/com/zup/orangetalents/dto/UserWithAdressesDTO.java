package br.com.zup.orangetalents.dto;

import br.com.zup.orangetalents.domain.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserWithAdressesDTO extends UserDTO{


    private List<AddressDTO> addressDTOList = new ArrayList<>();

    public UserWithAdressesDTO() {}

    public UserWithAdressesDTO(String name, String email, String cPf, LocalDate birthDate, List<AddressDTO> addressDTOList) {
        super(name, email, cPf, birthDate);
        this.addressDTOList = addressDTOList;
    }

    public static List<AddressDTO> addressListToAddressDTOList(List<Address> addressList) {
        return addressList.stream().map((Address address) -> AddressDTO.fromAddress(address))
                .collect(Collectors.toList());
    }

    @JsonProperty("addresses")
    public List<AddressDTO> getAddressDTO() {
        return addressDTOList;
    }

    public void setAddressDTO(List<AddressDTO> addressDTO) {
        this.addressDTOList = addressDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserWithAdressesDTO that = (UserWithAdressesDTO) o;
        return Objects.equals(addressDTOList, that.addressDTOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addressDTOList);
    }
}
