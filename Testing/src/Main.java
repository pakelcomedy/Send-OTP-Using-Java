import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {
    public static void main(String[] args) {
        // Database credentials
        String dbHost = "localhost";
        String dbPort = "3307"; // Check if this port is correct for your MySQL server
        String dbUser = "root";
        String dbPassword = "";
        String dbName = "project_laundryku";

        // Email credentials
        String myEmail = "visimaladesktop@gmail.com";
        String password = "cqos cciu knat feyp"; // Consider using more secure methods for handling passwords

        // Set up properties for SMTP server
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Establish database connection
        try {
            // Construct the JDBC URL
            String jdbcUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            Statement statement = connection.createStatement();

            // Query to retrieve recipient email address and OTP from the user table
            String query = "SELECT email, Kode_OTP FROM user WHERE id_user = 1"; // Assuming the user's ID is 1
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String recipientEmail = resultSet.getString("email");
                String otp = resultSet.getString("Kode_OTP");

                // Read HTML content from file
                String htmlBody = readHtmlFile("index.html"); // Assuming index.html is in the same package

                // Replace placeholder in HTML content with OTP
                htmlBody = htmlBody.replace("<div class=\"otp\"></div>", "<div class=\"otp\">" + otp + "</div>");

                // Sending the email
                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(myEmail, password);
                    }
                });

                // Construct the email message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                message.setSubject("Reset Password");
                message.setContent(htmlBody, "text/html");

                // Send the message
                Transport.send(message);
                System.out.println("Email sent successfully!");
            } else {
                System.out.println("User not found in the database!");
            }

            // Close the database connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException | MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read HTML file content
    private static String readHtmlFile(String fileName) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }
}