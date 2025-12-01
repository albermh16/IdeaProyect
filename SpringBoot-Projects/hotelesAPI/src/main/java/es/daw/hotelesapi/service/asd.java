package es.daw.hotelesapi.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class asd {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("admin");
        String hash2 = encoder.encode("user");
        System.out.println(hash);
        System.out.println(hash2);
    }
}