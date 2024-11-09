package com.naverrain.core.services.impl;

import com.naverrain.persistence.dao.UserDao;
import com.naverrain.persistence.dto.UserDto;
import com.naverrain.persistence.dto.converter.UserDtoToUserConverter;
import com.naverrain.persistence.entities.User;
import com.naverrain.core.services.UserManagementService;
import com.naverrain.core.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MySqlUserManagementService implements UserManagementService {

    private static final String SUCCESSFULL_REGISTRATION_MESSAGE = "User is registered!";
    private static final String REGISTRATION_FAIL_MESSAGE = "The email is already used.";

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDtoToUserConverter userConverter;

    @Autowired
    private MailSender mailSender;




    @Override
    public String registerUser(User user) {
        boolean isCreated = userDao.saveUser(userConverter.convertUserToUserDto(user));
        if (isCreated){
            return SUCCESSFULL_REGISTRATION_MESSAGE;
        }
        else {
            return REGISTRATION_FAIL_MESSAGE;
        }
    }

    @Override
    public List<User> getUsers() {
        List<UserDto> userDtos = userDao.getUsers();
        return userConverter.convertUserDtosToUsers(userDtos);
    }

    @Override
    public User getUserByEmail(String userEmail) {
        UserDto userDto = userDao.getUserByEmail(userEmail);
        return userConverter.convertUserDtoToUser(userDto);
    }

    @Override
    public void resetPasswordForUser(User user) {
        mailSender.sendEmail(user.getEmail(), "Here is your password to login: " + user.getPassword());
    }
}
