package AndroidUtils;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class EmailUtility {

    public static void sendEmailWithHTMLReport(String subject, String recipients, String reportFilePath) {
        // SMTP Server details
        String from = "tech@testriq.com";  // Your email address
        String host = "smtp.gmail.com";  // SMTP host
        String username = "tech@testriq.com";  // Your email address (same as 'from')
        String password = "ygpnpyzghlwxszxx";  // Your email password
        int port = 465;  // Use port 465 for SSL connection

        // Set up the mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Read the HTML report content
        String htmlContent = readHTMLFile(reportFilePath);

        try {
            // Create a MimeMessage
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            
            // Split the recipients string by commas to handle multiple recipients
            String[] recipientArray = recipients.split(",");
            for (String recipient : recipientArray) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient.trim())); // Remove any extra spaces
            }
            
            message.setSubject(subject);
            
            // Set the HTML content of the email
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent, "text/html");

            // Combine the parts (in case you want to add more content)
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully with HTML report.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Method to read the HTML file content into a string
    private static String readHTMLFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath))) {
            int character;
            while ((character = fileInputStream.read()) != -1) {
                stringBuilder.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}

