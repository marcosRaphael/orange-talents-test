package util;

import br.com.zup.orangetalents.domain.Address;
import br.com.zup.orangetalents.domain.User;

import java.time.LocalDate;

public class AddressCreator {

    public static Address createValidAddress() {
        return new Address(1L, UserCreator.createValidUser(), "logradouro", "22"
                , "complemento", "bairro", "cidade", "estado", "13100000");
    }
}
