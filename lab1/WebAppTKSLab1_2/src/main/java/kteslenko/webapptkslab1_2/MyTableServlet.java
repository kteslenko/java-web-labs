package kteslenko.webapptkslab1_2;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/MyTableServlet")
public class MyTableServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        EmployeeList employees = EmployeeList.getInstance();

        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Table</title></head><body>");
        html.append("<h1>Table of employees:</h1>");
        html.append("<table border='1'><tr><th>Name</th><th>Birthday</th><th>Gender</th><th>Salary</th><th>Programming Language</th></tr>");

        for (Employee employee : employees) {
            html.append("<tr>");
            html.append("<td>").append(employee.getName()).append("</td>");
            html.append("<td>").append(employee.getBirthday()).append("</td>");
            html.append("<td>").append(employee.getGender()).append("</td>");
            html.append("<td>").append(employee.getSalaryCurrency()).append("</td>");
            html.append("<td>").append(employee.getProgramLanguage()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table></body></html>");

        html.append("<br><br><a href=\"index.jsp\">To START</a>");

        response.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }
}
