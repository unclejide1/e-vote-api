package com.systemspecs.evoting.usecases;

import com.systemspecs.evoting.usecases.data.request.SignUpRequest;

public interface SignUpUseCase {
    String processSignUp(SignUpRequest signUpRequest);
}
