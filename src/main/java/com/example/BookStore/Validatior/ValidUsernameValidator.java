package com.example.BookStore.Validatior;

import com.example.BookStore.Validatior.annotation.ValidUsername;
import jakarta.validation.ConstraintValidator;
import com.example.BookStore.repository.IUserRepository;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context){
        if(userRepository == null)
            return true;
        return userRepository.findByUsername(username) == null;
    }
}
