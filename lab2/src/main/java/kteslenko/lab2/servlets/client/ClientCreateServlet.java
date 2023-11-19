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

@WebServlet(name = "clientCreateServlet", value = "/client/create")
public class ClientCreateServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("client", new Client());

        request.getRequestDispatcher("/views/client_create.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Client client = extractClient(request);

        try {
            DaoManager.getClientDao().insert(client);
        } catch (ConstraintViolationException ex) {
            String error = constraintToErrorMessage(client, Optional.ofNullable(ex.getConstraintName()).orElse(""));
            request.setAttribute("error", error);
            request.setAttribute("client", client);
            request.getRequestDispatcher("/views/client_create.jsp").forward(request, response);
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
                .firstName(request.getParameter("first_name"))
                .lastName(request.getParameter("last_name"))
                .phone(request.getParameter("phone"))
                .email(request.getParameter("email"))
                .gender(Gender.valueOf(request.getParameter("gender")))
                .isVIP(Boolean.parseBoolean(request.getParameter("vip")))
                .build();
    }
}
