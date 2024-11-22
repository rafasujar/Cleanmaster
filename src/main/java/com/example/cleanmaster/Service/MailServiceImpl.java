package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.ReservarCitaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String miCorreo;

    @Override
    public void resetPassword(String email, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(miCorreo);
        message.setTo(email);
        message.setSubject("cambio de contraseña");
        message.setText("Su nueva contraseña es: " + password);
        emailSender.send(message);
    }

    @Override
    public void sendFacture(String email, ReservarCitaDTO reservarCitaDTO) {
        /*
        try {
             Esta seria la estructura de un mensaje con un archivo adjunto falta crear el pdf con la factura
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("noreply@baeldung.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    */
    }


}
