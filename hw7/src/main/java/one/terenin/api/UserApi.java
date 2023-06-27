package one.terenin.api;

import one.terenin.dto.JwtResponse;
import one.terenin.dto.UserLoginRequest;
import one.terenin.dto.UserRequest;
import one.terenin.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/user")
public interface UserApi {

    @PostMapping("/register")
    ResponseEntity<UserResponse> register(@RequestBody UserRequest request);

    @PostMapping("/login")
    ResponseEntity<JwtResponse> login(@RequestBody UserLoginRequest loginRequest);
}
