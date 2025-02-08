package com.experiment.daeseda_renewal.service.mail;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

public interface MailService {

    MimeMessage createMessage(String message, String verificationCode) throws MessagingException, UnsupportedEncodingException;
    String generateKey();
    String sendMessage(String email) throws Exception;
}
