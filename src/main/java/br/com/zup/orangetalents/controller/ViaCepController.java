package br.com.zup.orangetalents.controller;

import br.com.zup.orangetalents.client.ViaCepClient;
import br.com.zup.orangetalents.dto.AddressDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("viacep")
public class ViaCepController {

    private final ViaCepClient viaCepClient;

    public ViaCepController(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @GetMapping(path = "/consult/{cep}")
    public AddressDTO consult(@PathVariable String cep) {

        return viaCepClient.getAddress(cep);
    }
}
