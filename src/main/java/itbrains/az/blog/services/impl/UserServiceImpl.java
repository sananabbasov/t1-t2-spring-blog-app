package itbrains.az.blog.services.impl;

import itbrains.az.blog.dtos.authdtos.RegisterDto;
import itbrains.az.blog.models.User;
import itbrains.az.blog.repositories.UserRepository;
import itbrains.az.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean register(RegisterDto register) {

        User user = userRepository.findByEmail(register.getEmail());
        if (user != null){
            return false;
        }
        String hashPassword = bCryptPasswordEncoder.encode(register.getPassword());
        String token = bCryptPasswordEncoder.encode(register.getEmail());
        User newUser = modelMapper.map(register, User.class);
        newUser.setEmailConfirmed(false);
        newUser.setConfirmationToken(token);
        newUser.setPassword(hashPassword);
        userRepository.save(newUser);
        return true;
    }
}
