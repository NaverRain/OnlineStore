package com.naverrain.core.facades.impl;

import static  com.naverrain.persistence.dto.RoleDto.CUSTOMER_ROLE_NAME;

import com.naverrain.core.facades.UserFacade;
import com.naverrain.core.services.AffiliateMarketingService;
import com.naverrain.persistence.dao.UserDao;
import com.naverrain.persistence.dto.converter.UserDtoToUserConverter;
import com.naverrain.persistence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserFacade implements UserFacade{

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserDtoToUserConverter converter;

	@Autowired
	private AffiliateMarketingService marketingService;
	

	@Override
	public void registerUser(User user, String referrerCode) {
		user.setRoleName(CUSTOMER_ROLE_NAME);
		user.setPartnerCode(marketingService.generateUniquePartnerCode());
		user.setReferrerUser(converter.convertUserDtoToUser(userDao.getUserByPartnerCode(referrerCode)));
		userDao.saveUser(converter.convertUserToUserDto(user));
	}

	@Override
	public User getUserByEmail(String email) {
		return converter.convertUserDtoToUser(userDao.getUserByEmail(email));
	}

	@Override
	public List<User> getUsers() {
		return converter.convertUserDtosToUsers(userDao.getUsers());
	}

	@Override
	public User getUserById(Integer id) {
		return converter.convertUserDtoToUser(userDao.getUserById(id));
	}

	@Override
	public void updateUser(User referrerUser) {
		userDao.updateUser(converter.convertUserToUserDto(referrerUser));
	}

	@Override
	public List<User> getReferralsForUser(User user) {
		return converter.convertUserDtosToUsers(userDao.getReferralsByUserId(user.getId()));
	}
}
