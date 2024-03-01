Here's an updated README file with specific instructions on how to run the program and configure it:

---

# Email OTP Sender

This Java program retrieves an OTP (One-Time Password) from a database and sends it to a user via email for password reset purposes.

## Prerequisites

Before running the program, ensure that you have the following installed and set up:

1. **Java Development Kit (JDK)**: Ensure you have Java JDK 8 or later installed on your system. You can download and install it from the [official Oracle website](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html).

2. **MySQL Database**: You need access to a MySQL database server. Install MySQL if you haven't already. You can download MySQL from the [official MySQL website](https://dev.mysql.com/downloads/).

## Configuration

### Database Configuration

Modify the database credentials in the `Main.java` file to match your MySQL server configuration:

```java
String dbHost = "localhost";
String dbPort = "3306"; // Port number of your MySQL server
String dbUser = "root";
String dbPassword = "your_password"; // Password for your MySQL server
String dbName = "project_laundryku"; // Name of the database containing user information
```

### Email Configuration

Update the email credentials in the `Main.java` file with your email address and app password or email account password:

```java
String myEmail = "your_email@gmail.com"; // Your email address
String password = "your_email_password"; // Your email password or app password
```

Ensure that you allow less secure app access in your email settings or generate an app password if two-factor authentication is enabled.

### SMTP Configuration

Ensure that your SMTP server (e.g., Gmail SMTP) allows access from less secure apps:

- For Gmail, enable "Allow less secure apps" in your [Google account settings](https://myaccount.google.com/security).
- If you're using other email providers, ensure that SMTP access is allowed and configure the SMTP server properties accordingly in the `Main.java` file.

## Running the Program

1. **Compile the Java Program**: Open a terminal or command prompt, navigate to the directory containing the `Main.java` file, and compile the program using the following command:

   ```bash
   javac Main.java
   ```

2. **Run the Program**: After compiling successfully, run the program using the following command:

   ```bash
   java Main
   ```

3. **Verify Output**: Check the console output for messages indicating whether the email was sent successfully or if any errors occurred.

## Troubleshooting

- If you encounter any errors related to missing libraries or dependencies, ensure that you have correctly added the JavaMail API library to your project's classpath.
- Check your email provider's settings to ensure that SMTP access is enabled and configured correctly.

---

Feel free to customize the README file further according to your project's specific requirements and environment.
