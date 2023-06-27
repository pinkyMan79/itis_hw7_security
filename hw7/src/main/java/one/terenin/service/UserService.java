package one.terenin.service;

import one.terenin.dto.JwtResponse;
import one.terenin.dto.UserLoginRequest;
import one.terenin.dto.UserRequest;
import one.terenin.dto.UserResponse;

public interface UserService {

    JwtResponse login(UserLoginRequest loginRequest);
    UserResponse register(UserRequest request);

}
