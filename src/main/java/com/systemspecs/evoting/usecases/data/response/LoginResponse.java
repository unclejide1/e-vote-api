package com.systemspecs.evoting.usecases.data.response;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String username;
    private String jwt;
    private List<String> roles;
}
