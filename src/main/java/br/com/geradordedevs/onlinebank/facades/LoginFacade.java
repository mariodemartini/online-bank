package br.com.geradordedevs.onlinebank.facades;

import br.com.geradordedevs.onlinebank.dtos.requests.LoginRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.LoginResponseDTO;

public interface LoginFacade {

    LoginResponseDTO authentication(LoginRequestDTO loginRequestDTO);

}
