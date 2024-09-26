package com.gaurav.project.uber.uberApp.services;

public interface EmailSenderService {
    void sendEmail(String toEmail,String subject,String body);
    void sendEmail(String[] toEmail, String subject , String body);
}
