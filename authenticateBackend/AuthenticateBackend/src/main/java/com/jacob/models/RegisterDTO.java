package com.jacob.models;

/*
 *@title RegisterDTO
 *@description RegisterDTO used to handle the http request info
 *@author Jacob Sheldon
 *@version 1.0
 *@create 7/15/23 3:48 PM
 */
public class RegisterDTO {
    private String username;
    private String password;

    public RegisterDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
