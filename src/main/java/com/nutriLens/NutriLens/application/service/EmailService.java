package com.nutriLens.NutriLens.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationCode(String to, String code) {
        sendEmail(to, "NutriLens - Verifica tu correo electronico",
                "Tu codigo de verificacion es: " + code + "\n\n" +
                "Este codigo expira en 15 minutos.\n\n" +
                "Si no solicitaste este codigo, ignora este mensaje.");
    }

    public void sendPasswordResetCode(String to, String code) {
        sendEmail(to, "NutriLens - Restablece tu contrasena",
                "Tu codigo para restablecer la contrasena es: " + code + "\n\n" +
                "Este codigo expira en 15 minutos.\n\n" +
                "Si no solicitaste restablecer tu contrasena, ignora este mensaje.");
    }

    private void sendEmail(String to, String subject, String text) {
        if (fromEmail == null || fromEmail.isBlank()) {
            log.warn("Mail no configurado. No se pudo enviar email a: {} (subject: {})", to, subject);
            log.warn("Contenido del email:\nPara: {}\nAsunto: {}\n\n{}", to, subject, text);
            return;
        }
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            log.info("Email enviado exitosamente a: {}", to);
        } catch (Exception e) {
            log.error("Error enviando email a: {}", to, e);
            throw new RuntimeException("Error al enviar email: " + e.getMessage(), e);
        }
    }
}
