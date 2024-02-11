package com.epam.rd.autocode.assessment.basics.entity;

import java.math.BigDecimal;

public class Client extends User {

    protected BigDecimal balance;
    protected String driverLicense;

    public Client() {
    }

    public Client(long id, String email, String password, String name, BigDecimal balance, String driverLicense) {
        super(id, email, password, name);
        this.balance = balance;
        this.driverLicense = driverLicense;
    }

    @Override
    public String toString() {
        return String.format(
                "Client{%s, balance=%s, driverLicense='%s'}",
                super.toString(), balance, driverLicense);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
        result = prime * result + ((driverLicense == null) ? 0 : driverLicense.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (balance == null) {
            if (other.balance != null)
                return false;
        } else if (!balance.equals(other.balance))
            return false;
        if (driverLicense == null) {
            if (other.driverLicense != null)
                return false;
        } else if (!driverLicense.equals(other.driverLicense))
            return false;
        return true;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

}
