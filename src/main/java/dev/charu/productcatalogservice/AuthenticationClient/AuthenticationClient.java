package dev.charu.productcatalogservice.AuthenticationClient;

import dev.charu.productcatalogservice.AuthenticationClient.Dtos.ValidateTokenRequestDto;
import dev.charu.productcatalogservice.AuthenticationClient.Dtos.ValidatetokenResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class AuthenticationClient {
    private RestTemplateBuilder restTemplateBuilder;

    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public ValidatetokenResponseDto validate(String token, Long userId) {
        //RestTemplate restTemplate = restTemplateBuilder.build();
        ValidateTokenRequestDto request = new ValidateTokenRequestDto();
        request.setToken(token);
        request.setUserId(userId);
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();


       ValidatetokenResponseDto l = restTemplate.postForEntity(
                "http://localhost:9000/auth/validate",
                request,
                ValidatetokenResponseDto.class
        ).getBody();
        //ValidatetokenResponseDto validatetokenResponseDto=l.getBody();




        return l;
    }
}
