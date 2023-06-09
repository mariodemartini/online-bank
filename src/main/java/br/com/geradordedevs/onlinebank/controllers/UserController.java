package br.com.geradordedevs.onlinebank.controllers;

import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.onlinebank.facades.UserFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping
    public UserResponseDTO saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws Exception {
        return userFacade.saveUser(userRequestDTO);
    }

    @GetMapping
    public List<UserResponseDTO> listUsers(){
        return userFacade.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id){
        return userFacade.findById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id,@Valid @RequestBody UserRequestDTO userRequestDTO){
        return userFacade.updateUser(id, userRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userFacade.deleteUser(id);
    }

}
