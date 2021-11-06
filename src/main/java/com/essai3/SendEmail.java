package com.essai3;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class SendEmail {
    public static void sendEmail() {
        String to = "fromaddress@gmail.com";
        String from = "medijakk@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", "mekk1968");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setText("text");
            message.setSubject("subject");
            message.addRecipients(Message.RecipientType.TO, "mekkouri.mohamed.mpsi@gmail.com");
            message.addRecipients(Message.RecipientType.CC, "mekkouri.mohamed.mpsi@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Transport transport = null;
        try {
            transport = session.getTransport("smtp");
            transport.connect("medijakk@gmail.com", "mekk1968");
            transport.sendMessage(message, new Address[] { new InternetAddress("mekkouri.mohamed.mpsi@gmail.com"),
                    new InternetAddress("mekkouri.mohamed.mpsi@gmail.com") });
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (transport != null) {
                    transport.close();
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        sendEmail();
    }
}
