package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.ReservarCitaDTO;

public interface MailService {
    void resetPassword(String email, String password);
    void sendFacture(String email, String mensaje);


}
