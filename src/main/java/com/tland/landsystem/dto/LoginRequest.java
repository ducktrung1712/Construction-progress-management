package com.tland.landsystem.dto;

public class LoginRequest {
    private String account;
    private String password;

    // Getter cho account
    public String getAccount() {
        return account;
    }

    // Setter cho account (nếu cần)
    public void setAccount(String account) {
        this.account = account;
    }

    // Getter cho password
    public String getPassword() {
        return password;
    }

    // Setter cho password (nếu cần)
    public void setPassword(String password) {
        this.password = password;
    }
}
