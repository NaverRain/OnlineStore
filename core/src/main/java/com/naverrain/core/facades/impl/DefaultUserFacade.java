package com.naverrain.core.facades.impl;

import static  com.naverrain.persistence.dto.RoleDto.CUSTOMER_ROLE_NAME;

import com.naverrain.core.facades.UserFacade;
import com.naverrain.core.services.AffiliateMarketingService;
import com.naverrain.persistence.SetupDataLoader;
import com.naverrain.persistence.dao.RoleDao;
import com.naverrain.persistence.dao.UserDao;
import com.naverrain.persistence.dto.converter.RoleDtoToRoleConverter;
import com.naverrain.persistence.dto.converter.UserDtoToUserConverter;
import com.naverrain.persistence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DefaultUserFacade implements UserFacade{

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RoleDtoToRoleConverter roleConverter;

	@Autowired
	private UserDtoToUserConverter userConverter;

	@Autowired
	private AffiliateMarketingService marketingService;


	@Override
	public void registerUser(User user, String referrerCode) {
		user.setPartnerCode(marketingService.generateUniquePartnerCode());
		user.setReferrerUser(userConverter.convertUserDtoToUser(userDao.getUserByPartnerCode(referrerCode)));
		user.setRoles(roleConverter.convertRoleDtosToRoles(Arrays.asList(roleDao.getRoleByRoleName(SetupDataLoader.ROLE_CUSTOMER))));
		userDao.saveUser(userConverter.convertUserToUserDto(user));
	}

	@Override
	public User getUserByEmail(String email) {
		return userConverter.convertUserDtoToUser(userDao.getUserByEmail(email));
	}

	@Override
	public List<User> getUsers() {
		return userConverter.convertUserDtosToUsers(userDao.getUsers());
	}

	@Override
	public User getUserById(Integer id) {
		return userConverter.convertUserDtoToUser(userDao.getUserById(id));
	}

	@Override
	public void updateUser(User referrerUser) {
		userDao.updateUser(userConverter.convertUserToUserDto(referrerUser));
	}

	@Override
	public List<User> getReferralsForUser(User user) {
		return userConverter.convertUserDtosToUsers(userDao.getReferralsByUserId(user.getId()));
	}
}
