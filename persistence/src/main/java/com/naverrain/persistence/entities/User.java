package com.naverrain.persistence.entities;

import java.util.List;

public interface User {

    String getFirstName();
    void setFirstName(String firstName);

    String getLastName();
    void setLastName(String lastName);

    String getPassword();

    String getEmail();

    int getId();
    void setId(int id);

    void setPassword(String newPassword);
    void setEmail(String newEmail);

    List<Role> getRoles();
    void setRoles(List<Role> convertRoleDtosToRoles);

    double getMoney();
    void setMoney(double money);

    String getCreditCard();
    void setCreditCard(String creditCard);
    
    void setPartnerCode(String partnerCode);
    String getPartnerCode();
    
    void setReferrerUser(User convertUserDtoToUser);
    User getReferrerUser();

    boolean isEnabled();
    void setIsEnabled(boolean isEnabled);
}
