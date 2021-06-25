package com.spring.application.controller;

import com.spring.application.dto.AuthRequest;
import com.spring.application.dto.UserDto;
import com.spring.application.dto.UserResponse;
import com.spring.application.service.inter.UserService;
import com.spring.application.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.spring.application.enums.SuccesMessage.SUCCES_MESSAGE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @GetMapping(value="/account/me")
    public UserResponse signIn(@RequestHeader(value="Authorization") String token) {
        token = token.substring(7);
        String userName = jwtUtil.extractUsername(token);
        return UserResponse.builder()
                .username(userName).build();
    }

    @PostMapping("/authenticate")
    public String generateToken(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }

    @PostMapping("/account/signup")
    public ResponseEntity<String> userRegistration(@Valid @RequestBody UserDto userDto){

        userService.setUser(userDto);

        return new ResponseEntity<String>(SUCCES_MESSAGE.getMessage(), HttpStatus.CREATED);
    }
}
