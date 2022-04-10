package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class UserLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
     public UserLogoutServlet() {
        super();
    }
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         HttpSession session = request.getSession(false);
         if (session != null) {
             session.removeAttribute("user");
             RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
             dispatcher.forward(request, response);
         }
     }
    }
