package com.pac.smcc.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class EncriptarPassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        var password="asd";
        String encodedPassword = "$2a$10$ZSOI9qJgrr2s2ykqigDPW.nAZIHDMgMZWc1fnHceuTMaMjj25LmPK";


//        String encodedPassword = encoder.encode(password);
        System.out.println("password: "+password);
        System.out.println("password codificado: "+encodedPassword);

//        System.out.println("password encriptado: "+encriptarPassword(password));

        boolean isPasswordMatch = encoder.matches("asd", encodedPassword);
        System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);


        String str = "Lucas";
        String[] splited = str.split("\\s+");
        System.out.println(splited[0]);

    }

    public static String encriptarPassword(String password){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
