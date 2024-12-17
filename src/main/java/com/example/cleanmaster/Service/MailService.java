package com.example.cleanmaster.Service;

public interface MailService {
    void resetPassword(String email, String password);

    void sendFacture(String email, String mensaje);


}
