package br.com.geradordedevs.picpaychallenge.clients;

import br.com.geradordedevs.picpaychallenge.dtos.responses.AuthorizationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="picpay-api", url="https://run.mocky.io")
public interface AuthorizationClient {

    @GetMapping("/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6")
    AuthorizationResponseDTO authorization();
}
