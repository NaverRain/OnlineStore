package com.naverrain.core.services.impl;

import java.util.Random;

import com.naverrain.core.services.AffiliateMarketingService;
import com.naverrain.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultAffiliateMarketingService implements AffiliateMarketingService {

	private static final int MAX_CHARACTERS = 9;
	
	private static final char[] PARTNER_CODE_CHARSET = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1',
        '2', '3', '4', '5', '6', '7', '8', '9'};

	private static final Random RANDOM = new Random();

	@Autowired
	private UserDao userDao;
	
	@Override
	public String generateUniquePartnerCode() {
		String partnerCode;
		do {
			partnerCode = generatePartnerCode();
		}
		while (userDao.getUserByPartnerCode(partnerCode) != null);
		return partnerCode;
	}

	private String generatePartnerCode(){
		StringBuilder sb = new StringBuilder(MAX_CHARACTERS);
		for (int i = 0; i < MAX_CHARACTERS; i++) {
			sb.append(PARTNER_CODE_CHARSET[RANDOM.nextInt(PARTNER_CODE_CHARSET.length)]);
		}
		return sb.toString();
	}
}
