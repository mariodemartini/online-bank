package br.com.geradordedevs.onlinebank.mappers;

import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.onlinebank.entities.UserEntity;
import br.com.geradordedevs.onlinebank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserMapper {

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO toDTO(UserEntity userEntity){
        log.info("converting entity{} to dto", userEntity);
        return mapper.map(userEntity, UserResponseDTO.class);
    }

    public UserEntity toEntity(UserRequestDTO userRequestDTO){
        log.info("converting  dto {} to entity", userRequestDTO);
        return mapper.map(userRequestDTO, UserEntity.class);
    }

    public List<UserResponseDTO> toListDTO(Iterable<UserEntity> list){
        log.info("converting entity list {} to dto list", list);
        List<UserEntity> result = new ArrayList<>();
        list.forEach(result::add);
        return result.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
