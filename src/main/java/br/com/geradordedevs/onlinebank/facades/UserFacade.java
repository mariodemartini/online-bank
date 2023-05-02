package br.com.geradordedevs.onlinebank.facades;

import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.UserResponseDTO;

import java.util.List;

public interface UserFacade {

    List<UserResponseDTO> getUsers();

    UserResponseDTO saveUser(UserRequestDTO userRequestDTO) throws Exception;

    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    UserResponseDTO findById(Long id);

    void deleteUser(Long id);
}
