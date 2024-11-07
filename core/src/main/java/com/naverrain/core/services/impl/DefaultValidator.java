package com.naverrain.core.services.impl;

import com.naverrain.core.services.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DefaultValidator implements Validator {

    private static DefaultValidator instance;

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final String NULL_PASSWORD_VALUE_ERROR = "NULL password value";
    public static final String MOST_COMMON_PASSWORD = "Most common password";
    public static final String LENGTH_OR_SPECIAL_CHARACTER_ERROR = "Invalid max length or absence of special characters";
    private static final String COMMON_PASSWORDS_FILE_NAME = "10000_common_passwords.txt";

    @Override
    public boolean isValid(String password) {
        if (password.length() < 8){
            return false;
        }

        String specialCharacterString = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        for (int i = 0; i < password.length(); i++){
            char ch = password.charAt(i);
            if (specialCharacterString.contains(Character.toString(ch))){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_PATTERN);
    }

    public static synchronized DefaultValidator getInstance(){
        if (instance == null){
            instance = new DefaultValidator();
        }
        return instance;
    }

    @Override
    public List<String> validate(String password) {
        List<String> errorMessages = new ArrayList<>();
        if (password == null) {
            errorMessages.add(NULL_PASSWORD_VALUE_ERROR);
            return errorMessages;
        }
        if (!isValid(password)) {
            errorMessages.add(LENGTH_OR_SPECIAL_CHARACTER_ERROR);
        }
        InputStream is = getClass().getClassLoader()
                .getResourceAsStream(COMMON_PASSWORDS_FILE_NAME);
        try {
            List<String> mostCommonPasswords = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while( (line = reader.readLine()) != null) {
                mostCommonPasswords.add(line);
            }
            if (mostCommonPasswords.contains(password)) {
                errorMessages.add(MOST_COMMON_PASSWORD);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorMessages;
    }
}
