package com.pac.smcc.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        var password="1";
        String encodedPassword = encoder.encode(password);
        System.out.println("password: "+password);
        System.out.println("password codificado: "+encodedPassword);

//        System.out.println("password encriptado: "+encriptarPassword(password));

        boolean isPasswordMatch = encoder.matches("12", encodedPassword);
        System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);
    }

    public static String encriptarPassword(String password){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
