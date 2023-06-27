package one.terenin.controller;

import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import one.terenin.api.UserApi;
import one.terenin.dto.JwtResponse;
import one.terenin.dto.UserLoginRequest;
import one.terenin.dto.UserRequest;
import one.terenin.dto.UserResponse;
import one.terenin.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UserService service;

    @Override
    public ResponseEntity<UserResponse> register(UserRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @Override
    public ResponseEntity<JwtResponse> login(UserLoginRequest loginRequest) {
        return ResponseEntity.ok(service.login(loginRequest));
    }
}
