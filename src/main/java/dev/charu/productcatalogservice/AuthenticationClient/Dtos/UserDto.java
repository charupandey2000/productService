package dev.charu.productcatalogservice.AuthenticationClient.Dtos;

import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
public class UserDto {
    private String email;
    private Set<Role> roles = new HashSet<>();
}
