package servlet.homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDao userDao = new UserDao();

        try {
            userDao.insert(firstName, lastName, age, email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can`t add user!");
        }

        req.setAttribute("userEmail", email);
        req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
    }
}
