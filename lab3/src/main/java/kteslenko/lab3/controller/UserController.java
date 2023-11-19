package kteslenko.lab3.controller;

import kteslenko.lab3.model.User;
import kteslenko.lab3.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
public class UserController {
    UserService userService;

    @GetMapping("/users")
    public String showUsers(Model model, @AuthenticationPrincipal User auth) {
        List<User> users = userService.getAllUsersExcept(auth);

        model.addAttribute("auth", auth);
        model.addAttribute("users", users);

        return "user/users";
    }

    @GetMapping("/user/create")
    public String showCreateUserForm(Model model, @AuthenticationPrincipal User auth) {
        model.addAttribute("auth", auth);
        model.addAttribute("user", new User());

        return "user/create";
    }

    @PostMapping("/user/create")
    public String createUser(Model model, @AuthenticationPrincipal User auth, @ModelAttribute User user) {
        try {
            userService.insertUser(user);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("auth", auth);
            model.addAttribute("user", user);
            model.addAttribute("error", ex.getMessage());

            return "user/create";
        }

        return "redirect:/users";
    }

    @GetMapping("/user/{id}/edit")
    public String showEditUserForm(Model model, @AuthenticationPrincipal User auth, @PathVariable Long id) {
        User user = userService.getUserById(id);

        model.addAttribute("auth", auth);
        model.addAttribute("user", user);

        return "user/edit";
    }

    @PostMapping("/user/{id}/edit")
    public String editUser(Model model, @AuthenticationPrincipal User auth,
                           @PathVariable Long id, @ModelAttribute User user) {
        try {
            userService.updateUser(id, user);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("auth", auth);
            model.addAttribute("user", user);
            model.addAttribute("error", ex.getMessage());

            return "user/edit";
        }

        return "redirect:/users";
    }

    @PostMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.deleteUserById(id);

        return "redirect:/users";
    }
}
