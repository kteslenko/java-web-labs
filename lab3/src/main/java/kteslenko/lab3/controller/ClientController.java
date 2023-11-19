package kteslenko.lab3.controller;

import kteslenko.lab3.model.Client;
import kteslenko.lab3.model.User;
import kteslenko.lab3.service.ClientService;
import kteslenko.lab3.specifications.ClientSpecification;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/")
    public String showStartingPage() {
        return "redirect:/clients";
    }

    @GetMapping("/clients")
    public String showClients(Model model, @AuthenticationPrincipal User user, @ModelAttribute ClientSpecification specification) {
        List<Client> clients = clientService.getClientsBySpecification(specification);

        model.addAttribute("user", user);
        model.addAttribute("clients", clients);
        model.addAttribute("specification", specification);

        return "client/clients";
    }

    @GetMapping("/client/create")
    public String showCreateClientForm(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        model.addAttribute("client", new Client());

        return "client/create";
    }

    @PostMapping("/client/create")
    public String createClient(Model model, @AuthenticationPrincipal User user, @ModelAttribute Client client) {
        try {
            clientService.insertClient(client);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("user", user);
            model.addAttribute("client", client);
            model.addAttribute("error", ex.getMessage());

            return "client/create";
        }

        return "redirect:/clients";
    }

    @GetMapping("/client/{id}/edit")
    public String showEditClientForm(Model model, @AuthenticationPrincipal User user, @PathVariable Long id) {
        Client client = clientService.getClientById(id);

        model.addAttribute("user", user);
        model.addAttribute("client", client);

        return "client/edit";
    }

    @PostMapping("/client/{id}/edit")
    public String editClient(Model model, @AuthenticationPrincipal User user,
                             @PathVariable Long id, @ModelAttribute Client client) {
        try {
            clientService.updateClient(id, client);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("user", user);
            model.addAttribute("client", client);
            model.addAttribute("error", ex.getMessage());

            return "client/edit";
        }

        return "redirect:/clients";
    }

    @PostMapping("/client/{id}/delete")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);

        return "redirect:/clients";
    }
}
