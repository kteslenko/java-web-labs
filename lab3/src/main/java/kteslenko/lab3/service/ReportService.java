package kteslenko.lab3.service;

import jakarta.mail.MessagingException;

public interface ReportService {
    void sendReport(String email) throws MessagingException;
}
