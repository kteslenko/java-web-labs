package kteslenko.lab2.servlets.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kteslenko.lab2.entity.Client;
import kteslenko.lab2.entity.Gender;
import kteslenko.lab2.hibernate.DaoManager;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "clientEditServlet", value = "/client/edit")
public class ClientEditServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<Client> client = DaoManager.getClientDao().findById(id);

        if (client.isEmpty()) {
            request.setAttribute("error", "Client with id %d was not found".formatted(id));
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("client", client.get());

        request.getRequestDispatcher("/views/client_edit.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Client client = extractClient(request);

        if (DaoManager.getClientDao().findById(client.getId()).isEmpty()) {
            request.setAttribute("error", "Client with id %d was not found".formatted(client.getId()));
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }

        try {
            DaoManager.getClientDao().update(client);
        } catch (ConstraintViolationException ex) {
            String error = constraintToErrorMessage(client, Optional.ofNullable(ex.getConstraintName()).orElse(""));
            request.setAttribute("error", error);
            request.setAttribute("client", client);
            request.getRequestDispatcher("/views/client_edit.jsp").forward(request, response);
        }

        response.sendRedirect(request.getContextPath() + "/clients");
    }

    private String constraintToErrorMessage(Client client, String constraint) {
        return switch (constraint) {
            case "client_phone_key" -> "Client with phone %s already exists".formatted(client.getPhone());
            case "client_email_key" -> "Client with email %s already exists".formatted(client.getEmail());
            default -> "";
        };
    }

    private Client extractClient(ServletRequest request) {
        return Client.builder()
                .id(Long.parseLong(request.getParameter("id")))
                .firstName(request.getParameter("first_name"))
                .lastName(request.getParameter("last_name"))
                .phone(request.getParameter("phone"))
                .email(request.getParameter("email"))
                .gender(Gender.valueOf(request.getParameter("gender")))
                .isVIP(Boolean.parseBoolean(request.getParameter("vip")))
                .build();
    }
}
