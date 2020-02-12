package services.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Duels")
public class Duels extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: 09.02.2020 cleaning active session in bd 
        if (req.getParameter("action").equals("exit")) {
            req.getSession().invalidate();
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            req.setAttribute("rating", 1);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/Duels.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
