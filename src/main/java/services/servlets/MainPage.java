package services.servlets;

import model.User;
import services.db.JDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/MainPage")
public class MainPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        JDBC jdbc = new JDBC();
        User user = jdbc.getUserByName(name);
        if (user == null) {
            jdbc.createUser(name, password, new Date(System.currentTimeMillis()));
            user = jdbc.getUserByName(name);
        } else if (!user.getPassword().equals(password)) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/WrongPassword.jsp");
            requestDispatcher.forward(req, resp);
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/MainPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
