package kteslenko.lab2.servlets.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kteslenko.lab2.entity.Client;
import kteslenko.lab2.entity.Gender;
import kteslenko.lab2.hibernate.ClientFilter;
import kteslenko.lab2.hibernate.ClientSort;
import kteslenko.lab2.hibernate.ClientSort.Column;
import kteslenko.lab2.hibernate.ClientSort.Direction;
import kteslenko.lab2.hibernate.DaoManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "clientListServlet", value = "/clients")
public class ClientListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ClientFilter filter = extractFilter(request);
        ClientSort sort = extractSort(request);
        List<Client> clients = DaoManager.getClientDao().findByCriteria(filter, sort);

        request.setAttribute("filter", filter);
        request.setAttribute("sort", sort);
        request.setAttribute("clients", clients);

        request.getRequestDispatcher("/views/clients.jsp").forward(request, response);
    }

    private ClientFilter extractFilter(ServletRequest request) {
        ClientFilter filter = new ClientFilter("", "", "", "", Collections.emptyList(), null);

        Optional.ofNullable(request.getParameter("first_name_filter"))
                .ifPresent(filter::setFirstName);

        Optional.ofNullable(request.getParameter("last_name_filter"))
                .ifPresent(filter::setLastName);

        Optional.ofNullable(request.getParameter("phone_filter"))
                .ifPresent(filter::setPhone);

        Optional.ofNullable(request.getParameter("email_filter"))
                .ifPresent(filter::setEmail);

        Optional.ofNullable(request.getParameterValues("gender_filter"))
                .map(genders -> Arrays.stream(genders)
                        .map(Gender::valueOf)
                        .toList()
                ).ifPresent(filter::setGenders);

        Optional.ofNullable(request.getParameter("vip_filter"))
                .filter(string -> !string.equals("All"))
                .map(Boolean::parseBoolean)
                .ifPresent(filter::setIsVIP);

        return filter;
    }

    private ClientSort extractSort(ServletRequest request) {
        ClientSort sort = new ClientSort(Column.FIRST_NAME, Direction.ASC);

        Optional.ofNullable(request.getParameter("sort_column"))
                .map(Column::valueOf)
                .ifPresent(sort::setColumn);

        Optional.ofNullable(request.getParameter("sort_direction"))
                .map(Direction::valueOf)
                .ifPresent(sort::setDirection);

        return sort;
    }
}
