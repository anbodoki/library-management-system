package com.lms.atom.notofication.mail;

public interface EmailService {

    void sendMail(String to, String subject, String text) throws Exception;
}
