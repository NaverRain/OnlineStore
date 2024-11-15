package com.naverrain.persistence.entities.impl;

import java.util.List;
import java.util.Objects;

import com.naverrain.persistence.entities.Role;
import com.naverrain.persistence.entities.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class DefaultUser implements User {
    private static int userCounter = 0;

    private int id;

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 3, max = 25, message = "First name should have from 3 to 25 characters")
    private String firstName;
    
    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 3, max = 25, message = "Last name should have from 3 to 25 characters")
    private String lastName;

    private String password;
    private String repeatPassword;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Please, use a real email")
    private String email;

    private double money;
    private String creditCard;

    private String partnerCode;
    private User referrerUser;

    private List<Role> roles;
    private boolean isEnabled;

    {
        id = ++userCounter;
    }

    public DefaultUser(){
    }

    public DefaultUser(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public DefaultUser(int id, String firstName, String lastName, String password, String email){
        this.id = id;
        userCounter--;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public DefaultUser(String firstName, String lastName, String password, String email, String creditCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.creditCard = creditCard;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public double getMoney() {
        return money;
    }

    @Override
    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String getCreditCard() {
        return creditCard;
    }

    @Override
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public void setPassword(String newPassword) {
        if (newPassword != null && !newPassword.isEmpty()) {
            this.password = newPassword;
        }
    }

    public String getRepeatPassword(){
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword){
        this.repeatPassword = repeatPassword;
    }

    @Override
    public void setEmail(String newEmail) {
        if (newEmail == null) return;

        this.email = newEmail;
    }

    @Override
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
		
	}

	@Override
	public String getPartnerCode() {
		return this.partnerCode;
	}

	@Override
	public void setReferrerUser(User referrerUser) {
		this.referrerUser = referrerUser;
	}

	@Override
	public User getReferrerUser() {
		return this.referrerUser;
	}

	@Override
    public String toString() {
        return "ID: " + this.getId() + "\t\t" +
                "First Name: " + this.getFirstName() + "\t\t" +
                "Last Name: " + this.getLastName() + "\t\t" +
                "Email: " + this.getEmail() + "\t\t" +
                "Referrer user: " + this.getReferrerUser();

    }

    @Override
    public void setRoles(List<Role> convertRoleDtosToRoles) {
        this.roles = convertRoleDtosToRoles;
    }

    @Override
    public List<Role> getRoles() {
        return this.roles;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
