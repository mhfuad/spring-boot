package com.fuad.security.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotNull
    @Size(min = 3, max = 20)
    private String firstName;
    @NotNull
    @Size(min = 3, max = 20)
    private String lastName;
    @NotNull
    @Size(min = 3, max = 20)
    private String email;
    @NotNull
    @Size(min = 4, max = 20)
    private String password;
    @NotNull
    @Size(min = 3, max = 20)
    private String roles;
}
