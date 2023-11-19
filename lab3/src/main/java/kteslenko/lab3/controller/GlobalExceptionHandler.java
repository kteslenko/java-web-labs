package kteslenko.lab3.controller;

import kteslenko.lab3.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(Model model, @AuthenticationPrincipal User user, NoSuchElementException exception) {
        model.addAttribute("message", "Entity Not Found");
        model.addAttribute("cause", exception.getMessage());
        model.addAttribute("user", user);

        return "error/default";
    }
}
