package itbrains.az.blog.services;

public interface EmailService {
    void sendConfirmationEmail(String email, String token);
}
