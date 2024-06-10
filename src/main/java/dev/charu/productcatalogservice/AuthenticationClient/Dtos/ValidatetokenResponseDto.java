package dev.charu.productcatalogservice.AuthenticationClient.Dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.support.SessionStatus;

@Getter
@Setter

public class ValidatetokenResponseDto {
    private UserDto userDto;
    private SessionStatus sessionStatus;
}
