package br.com.geradordedevs.onlinebank.facades.impl;

import br.com.geradordedevs.onlinebank.dtos.requests.LoginRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.LoginResponseDTO;
import br.com.geradordedevs.onlinebank.facades.LoginFacade;
import br.com.geradordedevs.onlinebank.services.LoginService;
import br.com.geradordedevs.onlinebank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginFacadeImpl implements LoginFacade {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Override
    public LoginResponseDTO authentication(LoginRequestDTO loginRequestDTO){
        userService.validatePassword(loginRequestDTO);

        return new LoginResponseDTO(loginService.generateToken(loginRequestDTO.getEmail()));
    }

}
