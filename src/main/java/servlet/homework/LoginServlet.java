package servlet.homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDao userDao = new UserDao();

        Optional<User> user = userDao.getByEmail(email);

        if (!user.isPresent()){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        if (user.get().getPassword().equals(password)){
            req.setAttribute("userEmail", email);
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
