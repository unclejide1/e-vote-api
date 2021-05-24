package com.systemspecs.evoting.usecases;

import com.systemspecs.evoting.usecases.data.request.LoginRequest;
import com.systemspecs.evoting.usecases.data.response.LoginResponse;

public interface LoginUseCase {
    LoginResponse processLogin(LoginRequest loginRequest);
}
