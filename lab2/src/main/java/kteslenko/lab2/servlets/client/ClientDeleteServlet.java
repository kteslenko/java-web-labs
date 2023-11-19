package kteslenko.lab2.servlets.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kteslenko.lab2.entity.Client;
import kteslenko.lab2.hibernate.DaoManager;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "clientDeleteServlet", value = "/client/delete")
public class ClientDeleteServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<Client> client = DaoManager.getClientDao().findById(id);

        if (client.isEmpty()) {
            request.setAttribute("error", "Client with id %d was not found".formatted(id));
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
            return;
        }

        DaoManager.getClientDao().delete(client.get());

        response.sendRedirect(request.getContextPath() + "/clients");
    }
}
