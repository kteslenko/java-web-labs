package kteslenko.webapptkslab1_1;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello, WebWorld!!!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<br>");
        out.println("<h2>Teslenko Kostiantyn Serhiyovich</h2>");
        out.println("<br>");
        out.println("<h3><i>KN-221b.e #10</i></h3>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}