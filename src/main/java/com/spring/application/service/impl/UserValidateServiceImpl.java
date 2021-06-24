package com.spring.application.service.impl;

import com.spring.application.repository.UserRepository;
import com.spring.application.service.inter.UserValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserValidateServiceImpl implements UserValidateService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public boolean existence(String username) {
        return userRepository.existsByName(username);
    }
}
