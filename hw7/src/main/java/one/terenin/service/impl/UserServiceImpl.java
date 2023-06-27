package one.terenin.service.impl;

import lombok.RequiredArgsConstructor;
import one.terenin.dto.JwtResponse;
import one.terenin.dto.UserLoginRequest;
import one.terenin.dto.UserRequest;
import one.terenin.dto.UserResponse;
import one.terenin.entity.UserEntity;
import one.terenin.mapper.UserMapper;
import one.terenin.repository.UserRepository;
import one.terenin.security.details.UserDetailsBase;
import one.terenin.security.token.filter.common.SecurityConstants;
import one.terenin.security.token.filter.common.util.JwtUtils;
import one.terenin.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final JwtUtils utils;
    private final UserMapper mapper;
    private final AuthenticationManager manager;

    @Override
    public JwtResponse login(UserLoginRequest loginRequest) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = utils.generateJwToken(authentication);
        UserDetailsBase userDetails = (UserDetailsBase) authentication.getPrincipal();
        return new JwtResponse(token,
                SecurityConstants.TOKEN_PREFIX,
                userDetails.getEntity().getId(),
                userDetails.getUsername(),
                Collections.singleton(userDetails.getEntity().getRole().toString()));
    }

    @Override
    public UserResponse register(UserRequest request) {
        UserEntity map = mapper.map(request);
        repository.save(map);
        return mapper.map(map);
    }
}
