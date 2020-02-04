package services.servlets;

import model.User;
import services.db.JDBCConnector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/Authentication")
public class Authentication extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        JDBCConnector jdbcConnector = new JDBCConnector();
        User user = jdbcConnector.getUser(name);
        if (user == null) {
            jdbcConnector.createUser(name, password, new Date(System.currentTimeMillis()));
        } else if (!user.getPassword().equals(password)) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/WrongPassword.jsp");
            requestDispatcher.forward(req, resp);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/MainPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
