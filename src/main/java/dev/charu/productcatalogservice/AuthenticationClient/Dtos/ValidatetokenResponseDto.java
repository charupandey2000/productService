package dev.charu.productcatalogservice.AuthenticationClient.Dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class ValidatetokenResponseDto {
    private UserDto userDto;
    private SessionStatus sessionStatus;
}
