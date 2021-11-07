package com.essai3;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
public class SendEmail {
    public static void sendEmail() throws MessagingException {
        String to = "fromaddress@gmail.com";
        String from = "medijakk@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");//465
        //properties.put("mail.smtp.user", from);
        //properties.put("mail.smtp.password", "mekk1968");
        //properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "mekk1968");
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setText("text");
            message.setSubject("subject");
            message.addRecipients(Message.RecipientType.TO, "mekkouri.mohamed.mpsi@gmail.com");
            message.addRecipients(Message.RecipientType.CC, "mekkouri.mohamed.mpsi@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        Transport.send(message);
//        Transport transport = null;
//        try {
//            transport = session.getTransport("smtp");
//            transport.connect("medijakk@gmail.com", "mekk1968");
//            transport.sendMessage(message, new Address[] { new InternetAddress("mekkouri.mohamed.mpsi@gmail.com"),
//                    new InternetAddress("mekkouri.mohamed.mpsi@gmail.com") });
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (transport != null) {
//                    transport.close();
//                }
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }
    }
    public static void sendEmail1() throws MessagingException, UnsupportedEncodingException {
        Email email = new Email("medijakk@gmail.com","mekk1968");
        email.setFrom("medijakk@gmail.com","1 hour music");
        email.setSubject("subject");
        email.setContent("text","text/html");
        email.addRecipient("mekkouri.mohamed.mpsi@gmail.com");
        email.send();


    }

    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
        sendEmail1();
    }
}
