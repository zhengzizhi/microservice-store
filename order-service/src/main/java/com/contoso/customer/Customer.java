package com.contoso.customer;

import java.io.Serializable;

import com.contoso.account.Account;
import com.contoso.data.BaseEntity;

/**
 * The {@link Customer} entity is a root object in the customer bounded context.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
public class Customer extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 4801508116215017080L;
	
	private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Account account;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", account=" + account +
                "} " + super.toString();
    }
}
