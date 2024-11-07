package com.naverrain.core.services;

import java.util.List;

public interface Validator {
    boolean isValid(String password);
    boolean isValidEmail(String email);
    List<String> validate (String password);
}
