package com.spring.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.spring.application.dto.AuthRequest;
import com.spring.application.dto.UserDto;
import com.spring.application.service.inter.UserService;
import com.spring.application.util.JwtUtil;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    public void testGenerateToken() throws Exception {
        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        UserController userController = new UserController(new JwtUtil(), authenticationManager, mock(UserService.class));
        assertThrows(Exception.class, () -> userController.generateToken(new AuthRequest("janedoe", "iloveyou")));
    }

    @Test
    public void testGenerateToken2() throws Exception {
        UserController userController = new UserController(new JwtUtil(), null, mock(UserService.class));
        assertThrows(Exception.class, () -> userController.generateToken(new AuthRequest("janedoe", "iloveyou")));
    }

    @Test
    public void testGenerateToken3() throws Exception {
        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
        authenticationProviderList.add(new DaoAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        UserController userController = new UserController(new JwtUtil(), authenticationManager, mock(UserService.class));
        assertThrows(Exception.class, () -> userController.generateToken(new AuthRequest("janedoe", "iloveyou")));
    }

    @Test
    public void testGenerateToken4() throws Exception {
        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        assertThrows(Exception.class,
                () -> (new UserController(new JwtUtil(), authenticationManager, mock(UserService.class))).generateToken(null));
    }

    @Test
    public void testGenerateToken5() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPreAuthenticationChecks(new AccountStatusUserDetailsChecker());

        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
        authenticationProviderList.add(daoAuthenticationProvider);
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        UserController userController = new UserController(new JwtUtil(), authenticationManager, mock(UserService.class));
        assertThrows(Exception.class, () -> userController.generateToken(new AuthRequest("janedoe", "iloveyou")));
    }

    @Test
    public void testSignIn() throws Exception {
        when(this.jwtUtil.extractUsername(anyString())).thenReturn("foo");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/account/me")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"username\":\"foo\"}"));
    }

    @Test
    public void testSignIn2() throws Exception {
        when(this.jwtUtil.extractUsername(anyString())).thenReturn("foo");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/account/me")
                .header("Authorization", "https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"username\":\"foo\"}"));
    }

    @Test
    public void testUserRegistration() {
        ArrayList<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
        authenticationProviderList.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
        UserService userService = mock(UserService.class);
        when(userService.setUser((UserDto) any())).thenReturn(true);
        ResponseEntity<String> actualUserRegistrationResult = (new UserController(new JwtUtil(), authenticationManager,
                userService)).userRegistration(mock(UserDto.class));
        assertEquals("You are signup succesfully", actualUserRegistrationResult.getBody());
        assertEquals("<201 CREATED Created,You are signup succesfully,[]>", actualUserRegistrationResult.toString());
        assertEquals(HttpStatus.CREATED, actualUserRegistrationResult.getStatusCode());
        assertTrue(actualUserRegistrationResult.getHeaders().isEmpty());
        verify(userService).setUser((UserDto) any());
    }
}

