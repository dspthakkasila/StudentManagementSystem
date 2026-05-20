package util;

import util.EmailUtil;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Authenticator;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

    public static void sendEmail(

            String toEmail,
            String subject,
            String body) {

        final String fromEmail =
                "thakkasiladurga@gmail.com";

        final String password =
                "vzda czar mejw hcln";

        Properties props =
                new Properties();

        props.put(
        "mail.smtp.host",
        "smtp.gmail.com");

        props.put(
        "mail.smtp.port",
        "587");

        props.put(
        "mail.smtp.auth",
        "true");

        props.put(
        "mail.smtp.starttls.enable",
        "true");

        Session session =
        Session.getInstance(

        props,

        new Authenticator() {

            protected
            PasswordAuthentication
            getPasswordAuthentication() {

                return new
                PasswordAuthentication(
                        fromEmail,
                        password);
            }
        });

        try {

            Message message =
                    new MimeMessage(session);

            message.setFrom(
                    new InternetAddress(
                            fromEmail));

            message.setRecipients(

            Message.RecipientType.TO,

            InternetAddress.parse(
                    toEmail));

            message.setSubject(subject);

//            message.setText(body);
            message.setContent(body, "text/html");

            Transport.send(message);

            System.out.println(
            "Email Sent Successfully");

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}