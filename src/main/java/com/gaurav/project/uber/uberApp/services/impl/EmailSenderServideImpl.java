package com.gaurav.project.uber.uberApp.services.impl;

import com.gaurav.project.uber.uberApp.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderServideImpl implements EmailSenderService {
    private final JavaMailSender javaMailSender;
    @Override
    public void sendEmail(String email, String subject, String body) {

        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
            log.info("Email sent Sucessfully");

        }

        catch (Exception e){
            log.info("Cannot send email, "+e.getMessage());
        }




    }

    @Override
    public void sendEmail(String[] toEmail, String subject, String body) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
            log.info("Email sent Sucessfully");

        }

        catch (Exception e){
            log.info("Cannot send email, "+e.getMessage());
        }



    }
}
