package br.com.geradordedevs.picpaychallenge.facades;

import br.com.geradordedevs.picpaychallenge.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.picpaychallenge.dtos.responses.UserResponseDTO;

import java.util.List;

public interface UserFacade {
    List<UserResponseDTO> getUsers();
    UserResponseDTO saveUser(UserRequestDTO userRequestDTO) throws Exception;
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    UserResponseDTO findById(Long id);
    void deleteUser(Long id);
}
