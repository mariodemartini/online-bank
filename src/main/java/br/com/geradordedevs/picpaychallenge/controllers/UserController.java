package br.com.geradordedevs.picpaychallenge.controllers;

import br.com.geradordedevs.picpaychallenge.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.picpaychallenge.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.picpaychallenge.facades.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping
    public UserResponseDTO saveUser(@RequestBody UserRequestDTO userRequestDTO) throws Exception {
        return userFacade.saveUser(userRequestDTO);
    }

    @GetMapping
    public Iterable<UserResponseDTO> listUsers(){
        return userFacade.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id){
        return userFacade.findById(id);
    }

    @PutMapping("{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO){
        return userFacade.updateUser(id, userRequestDTO);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id){
        userFacade.deleteUser(id);
    }
}
