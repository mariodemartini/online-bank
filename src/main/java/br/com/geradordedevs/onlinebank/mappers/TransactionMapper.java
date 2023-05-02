package br.com.geradordedevs.onlinebank.mappers;

import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
import br.com.geradordedevs.onlinebank.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionMapper {

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionResponseDTO toDTO(TransactionEntity transactionEntity){
        log.info("converting entity{} to dto", transactionEntity);
        return mapper.map(transactionEntity, TransactionResponseDTO.class);
    }

    public TransactionEntity toEntity(TransactionRequestDTO transactionRequestDTO){
        log.info("converting  dto {} to entity", transactionRequestDTO);
        return mapper.map(transactionRequestDTO, TransactionEntity.class);
    }

    public List<TransactionResponseDTO> toListDTO(Iterable<TransactionEntity> list){
        log.info("converting entity list {} to dto list", list);
        List<TransactionEntity> result = new ArrayList<>();
        list.forEach(result::add);
        return result.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
