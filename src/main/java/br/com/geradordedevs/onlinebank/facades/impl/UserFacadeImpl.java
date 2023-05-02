package br.com.geradordedevs.onlinebank.facades.impl;

import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.onlinebank.facades.UserFacade;
import br.com.geradordedevs.onlinebank.mappers.UserMapper;
import br.com.geradordedevs.onlinebank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserResponseDTO> getUsers() {
        return userMapper.toListDTO(userService.getUsers());
    }

    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) throws Exception {
        return userMapper.toDTO(userService.saveUser(userMapper.toEntity(userRequestDTO)));
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        return userMapper.toDTO(userService.updateUser(id, userMapper.toEntity(userRequestDTO)));
    }

    @Override
    public UserResponseDTO findById(Long id) {
        return userMapper.toDTO(userService.findById(id));
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

}
