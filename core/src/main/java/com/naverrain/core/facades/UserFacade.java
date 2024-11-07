package com.naverrain.core.facades;

import com.naverrain.persistence.entities.User;

import java.util.List;

public interface UserFacade {
	
	void registerUser(User user, String partnerCode);
	
	User getUserByEmail(String email);

	List<User> getUsers();

	User getUserById(Integer id);

	void updateUser(User referrerUser);

	List<User> getReferralsForUser(User user);
}
