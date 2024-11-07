package com.naverrain.persistence.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity(name = "user")
public class UserDto {

    @Id
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_role")
    private RoleDto roleDto;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "credit_card")
    private String creditCard;

    @Column(name = "password")
    private String password;

    @Column(name = "partner_code")
    private String partnerCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "referrer_user_id")
    private UserDto referrerUser;
    
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getPartnerCode() {
		return this.partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public UserDto getReferrerUser() {
		return this.referrerUser;
	}

	public void setReferrerUser(UserDto referrerUser) {
		this.referrerUser = referrerUser;
	}

    @Override
    public String toString() {
        return "UserDto [" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", roleDto=" + roleDto +
                ", money=" + money +
                ", creditCard='" + creditCard + '\'' +
                ", password='" + password + '\'' +
                ", partnerCode='" + partnerCode + '\'' +
                ", referrerUser=" + referrerUser +
                ']';
    }
}
