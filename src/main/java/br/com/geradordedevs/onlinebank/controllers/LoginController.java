package br.com.geradordedevs.onlinebank.controllers;

import br.com.geradordedevs.onlinebank.dtos.requests.LoginRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.LoginResponseDTO;
import br.com.geradordedevs.onlinebank.facades.LoginFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginFacade loginFacade;

    @PostMapping
    public LoginResponseDTO login(@Valid@RequestBody LoginRequestDTO loginRequestDTO){
        return loginFacade.authentication(loginRequestDTO);
    }

}
