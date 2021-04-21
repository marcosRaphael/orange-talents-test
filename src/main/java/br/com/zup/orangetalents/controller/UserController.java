package br.com.zup.orangetalents.controller;

import br.com.zup.orangetalents.client.ViaCepClient;
import br.com.zup.orangetalents.dto.AddressDTO;
import br.com.zup.orangetalents.dto.UserDTO;
import br.com.zup.orangetalents.dto.UserWithAdressesDTO;
import br.com.zup.orangetalents.service.AddressService;
import br.com.zup.orangetalents.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@CrossOrigin
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final AddressService addressService;

    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "create a new user in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user successfully created"),
            @ApiResponse(responseCode = "400", description = "when any of the fields is in the wrong format." +
                    " there is additional information in the body about all errors")
    })


    public ResponseEntity<Long> userSave(@RequestBody @Valid UserDTO userDTO) {
         Long userId = userService.save(userDTO);

         return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @PostMapping(path = "/{userId}/address")
    @Operation(summary = "Create a new user address", description = "create a new user address in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address user successfully created"),
            @ApiResponse(responseCode = "400", description = "When any of the fields is in the wrong format." +
                    " there is additional information in the body about all errors")
    })
    public ResponseEntity<Void> saveUserAddress(@RequestBody @Valid AddressDTO addressDTO
            , @PathVariable Long userId) {

        addressService.save(addressDTO, userId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{userId}/address")
    @Operation(summary = "Returns a user and their addresses"
            , description = "return a user and their addresses from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User and their addresses returned successfully"),
            @ApiResponse(responseCode = "400", description = "When any of the fields is in the wrong format." +
                    " the user was not found.")
    })
    public ResponseEntity<UserWithAdressesDTO> findUserAndAdressesById(@PathVariable Long userId) {

        return new ResponseEntity<>(addressService.findAllByUserId(userId), HttpStatus.OK);
    }
}
