package kteslenko.lab3.controller;

import kteslenko.lab3.model.Address;
import kteslenko.lab3.model.Client;
import kteslenko.lab3.model.User;
import kteslenko.lab3.service.AddressService;
import kteslenko.lab3.service.ClientService;
import kteslenko.lab3.specifications.AddressSpecification;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class AddressController {
    ClientService clientService;
    AddressService addressService;

    @GetMapping("/client/{clientId}/addresses")
    public String showAddresses(Model model, @AuthenticationPrincipal User user,
                                @PathVariable Long clientId, @ModelAttribute AddressSpecification specification) {
        Client client = clientService.getClientById(clientId);
        List<Address> addresses = addressService.getAddressByClientIdBySpecification(clientId, specification);

        model.addAttribute("user", user);
        model.addAttribute("client", client);
        model.addAttribute("addresses", addresses);
        model.addAttribute("specification", specification);

        return "address/addresses";
    }

    @GetMapping("/client/{clientId}/address/create")
    public String showCreateAddressForm(Model model, @AuthenticationPrincipal User user, @PathVariable Long clientId) {

        model.addAttribute("user", user);
        model.addAttribute("clientId", clientId);
        model.addAttribute("address", new Address());

        return "address/create";
    }

    @PostMapping("/client/{clientId}/address/create")
    public String createAddress(@PathVariable Long clientId, @ModelAttribute("addr") Address address) {
        addressService.insertAddress(clientId, address);

        return "redirect:/client/%d/addresses".formatted(clientId);
    }

    @GetMapping("/client/{clientId}/address/{id}/edit")
    public String showEditAddressForm(Model model, @AuthenticationPrincipal User user,
                                      @PathVariable Long clientId, @PathVariable Long id) {
        Address address = addressService.getAddressById(id);

        model.addAttribute("user", user);
        model.addAttribute("clientId", clientId);
        model.addAttribute("address", address);

        return "address/edit";
    }

    @PostMapping("/client/{clientId}/address/{id}/edit")
    public String editAddress(@PathVariable Long clientId, @PathVariable Long id, @ModelAttribute("addr") Address address) {
        addressService.updateAddress(clientId, id, address);

        return "redirect:/client/%d/addresses".formatted(clientId);
    }

    @PostMapping("/client/{clientId}/address/{id}/delete")
    public String deleteAddress(@PathVariable Long clientId, @PathVariable Long id) {
        addressService.deleteAddressById(id);

        return "redirect:/client/%d/addresses".formatted(clientId);
    }
}
