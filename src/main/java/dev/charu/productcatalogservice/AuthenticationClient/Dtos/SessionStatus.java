package dev.charu.productcatalogservice.AuthenticationClient.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


public enum SessionStatus {
    ACTIVE,
    EXPIRED,
    LOGGED_OUT,
    INVALID,
}
