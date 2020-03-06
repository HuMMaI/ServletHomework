package servlet.homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("signUp") != null){
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }

        if (req.getParameter("signIn") != null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
