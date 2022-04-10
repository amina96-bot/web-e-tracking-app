package Servlets;

import Connexion.DatabaseConnection;
import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Departement;
import model.User;

/**
 *
 * @author akhaldi
 */

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    Dao dao = new Dao(DatabaseConnection.getConnection());
    Dao dao2 = new Dao(DatabaseConnection.getConnection2()); 
    
    public UserLoginServlet() {
        super();
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
    
        try {        
            int userId=dao.getUserByUsernameAndPassword(username, password);
            User user = null;
            Departement departement = null;
            if (userId!=0) {
                user = dao.getUserById(userId);
                if(user.getRoles().contains("administrateur") || user.getRoles().contains("validator") ){
                    HttpSession session = request.getSession();                                                            
                    session.setAttribute("user", user);  
                    DashboardServlet dashboardServlet=new DashboardServlet();
                    dashboardServlet.doGet(request, response);
                }else{
                    response.setContentType("text/html");
                    PrintWriter pw=response.getWriter();
                    pw.println("<script type=\"text/javascript\">");
                    pw.println("alert('You dont have required authority!');");                              
                    pw.println("</script>");                                               
                    RequestDispatcher rd=request.getRequestDispatcher("index.html");
                    rd.include(request, response);
                }
                }else{
                response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Username and password not matching!');");                              
                pw.println("</script>");                                               
                RequestDispatcher rd=request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }        
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        
    }
}
