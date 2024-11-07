package com.naverrain.core.facades.impl;

import static  com.naverrain.persistence.dto.RoleDto.CUSTOMER_ROLE_NAME;

import com.naverrain.core.facades.UserFacade;
import com.naverrain.core.services.AffiliateMarketingService;
import com.naverrain.core.services.impl.DefaultAffiliateMarketingService;
import com.naverrain.persistence.dao.UserDao;
import com.naverrain.persistence.dao.impl.JpaUserDao;
import com.naverrain.persistence.dto.converter.UserDtoToUserConverter;
import com.naverrain.persistence.entities.User;

import java.util.List;

public class DefaultUserFacade implements UserFacade{

	private static DefaultUserFacade instance;
	private UserDao userDao = new JpaUserDao();
	private UserDtoToUserConverter converter = new UserDtoToUserConverter();
	private AffiliateMarketingService marketingService = new DefaultAffiliateMarketingService();
	
	public static synchronized DefaultUserFacade getInstance() {
		if (instance == null) {
			instance = new DefaultUserFacade();
		}
		return instance;
	}

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
