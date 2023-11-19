package kteslenko.webapptkslab1_4.servlet;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginCheckServlet", value = "/LoginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("user".equals(username) && "user".equals(password)) {
            response.sendRedirect("KTeslenkoLab1Task4_Main.jsp");
        } else {
            response.sendRedirect("KTeslenkoLab1Task4_Start.jsp");
        }
    }
}
