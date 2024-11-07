package com.naverrain.persistence.dao;

import com.naverrain.persistence.dto.UserDto;

import java.util.List;

public interface UserDao {

    boolean saveUser(UserDto user);

    List<UserDto> getUsers();

    UserDto getUserById(int id);

    UserDto getUserByEmail(String email);
    
    UserDto getUserByPartnerCode(String partnerCode);

    void updateUser(UserDto user);

    List<UserDto> getReferralsByUserId(int id);
}
