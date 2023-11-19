package kteslenko.lab3.controller;

import jakarta.mail.MessagingException;
import kteslenko.lab3.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@AllArgsConstructor
@Controller
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/report")
    public String sendReport(@ModelAttribute("email") String email) throws MessagingException {
        reportService.sendReport(email);

        return "redirect:/clients";
    }
}
