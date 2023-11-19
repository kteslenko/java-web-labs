package kteslenko.lab3.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kteslenko.lab3.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
    private final JavaMailSender sender;
    private final ClientService clientService;
    private final TemplateEngine thymeleafTemplateEngine;

    public void sendReport(String email) throws MessagingException {
        String report = generateReport();

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Clients Report");
        helper.setText("Report:");
        helper.addAttachment("report.html", new ByteArrayResource(report.getBytes()));

        sender.send(message);
    }

    private String generateReport() {
        List<Client> clients = clientService.getAllClients();

        Context context = new Context();
        context.setVariable("clients", clients);

        return thymeleafTemplateEngine.process("report.html", context);
    }
}
