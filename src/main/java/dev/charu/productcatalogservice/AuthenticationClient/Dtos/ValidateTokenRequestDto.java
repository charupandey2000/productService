package dev.charu.productcatalogservice.AuthenticationClient.Dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ValidateTokenRequestDto {
    private Long userId;
    private String token;
}
