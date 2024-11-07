package com.naverrain.persistence.entities.impl;

import com.naverrain.persistence.utils.validation.Validate;

import java.util.Objects;

import com.naverrain.persistence.entities.User;

public class DefaultUser implements User {
    private static int userCounter = 0;

    private int id;

    @Validate(pattern = "[a-zA-Z]+")
    private String firstName;
    
    @Validate(pattern = "[a-zA-Z]+")
    private String lastName;

    private String password;

    @Validate(pattern = ".+@.+")
    private String email;

    private String roleName;
    private double money;
    private String creditCard;

    private String partnerCode;
    private User referrerUser;
    
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
    public String getRoleName() {
        return this.roleName;
    }

    @Override
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    void clearState(){
        userCounter = 0;
    }

    public static void setCounter(int updatedCount){
        userCounter = updatedCount;
    }
    
	@Override
	public int hashCode() {
		return Objects.hash(creditCard, email, firstName, id, lastName, money, partnerCode, password, referrerUser,
				roleName);
	}
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultUser other = (DefaultUser) obj;
		return Objects.equals(creditCard, other.creditCard) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName)
				&& Double.doubleToLongBits(money) == Double.doubleToLongBits(other.money)
				&& Objects.equals(partnerCode, other.partnerCode) && Objects.equals(password, other.password)
				&& Objects.equals(referrerUser, other.referrerUser) && Objects.equals(roleName, other.roleName);
	}
}
