package br.com.zup.orangetalents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PagesController {

    @RequestMapping("/usuario")
    public String welcome() {
        return "UserRegister";
    }

    @RequestMapping("/usuario/{userId}/enderecos")
    public String addresses() {
        return "AddressRegister";
    }
}
