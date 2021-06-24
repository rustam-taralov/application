package com.spring.application.service.impl;

import com.spring.application.Domain.User;
import com.spring.application.dto.UserDto;
import com.spring.application.repository.UserRepository;
import com.spring.application.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public final ModelMapper modelMapper;

    @Override
    @Transactional
    public boolean setUser(UserDto user) {

        if(user!=null){
            userRepository.save(modelMapper.map(user, User.class));
            return true;
        }
        return false;
    }
}
