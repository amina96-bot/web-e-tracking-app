/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Connexion.DatabaseConnection;
import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Assignment;
import model.Employee;
import model.Kilometrage;
import model.Payment;
import model.Trajet;
import model.User;

/**
 *
 * @author akhaldi
 */
@WebServlet("/drivers")
public class DriversServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if(action==null){
            action="list";
        }
        String id;
        
        Dao dao = new Dao(DatabaseConnection.getConnection());
        Dao dao2 = new Dao(DatabaseConnection.getConnection2());  
        
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");        
        String destPage = "";
        
        switch (action) {
        case "list" :
             List<User> users;
             if(user.getRoles().contains("administrateur")){
                users=dao.getAllUsers();
            }else{
                users=dao.getAllUsersByRegion(user.getEmployee().getRegion());
            }              
            request.setAttribute("users", users);                         
            destPage = "view/drivers.jsp"; 
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);   
            break;
            case "detail" :                       
            id = request.getParameter("id"); 
            User driver=dao.getUserById(Integer.valueOf(id));                     
            request.setAttribute("driver", driver);               
            destPage = "view/driver_detail.jsp";  
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);            
        break;
        case "add":      
            List<Employee> employees = dao2.getAllEmployees();           
            request.setAttribute("employees", employees);
            destPage = "view/add-driver.jsp";
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            break;
        case "update":
            id = request.getParameter("id");
            driver=dao.getUserById(Integer.valueOf(id));        
            request.setAttribute("user", driver);
            destPage = "view/update-driver.jsp";
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            break;
        case "delete":          
            id = request.getParameter("id");
            if(dao.checkIfUserHasAffectation(id)){
                 response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Impossible de supprimer cet utilisateur! ');");  
                pw.println("history.back();");  
                pw.println("</script>"); 
            }else{
                dao.deleteUser(id);
                users = dao.getAllUsers();          
                request.setAttribute("users", users);
                destPage = "view/drivers.jsp";
                dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);
            }
            break;
            case "driver_assignments":
            id = request.getParameter("id");
            user=dao.getUserById(Integer.valueOf(id));
            List<Assignment> assignments = dao.getDriverAssignments(id);          
            request.setAttribute("assignments", assignments); 
            request.setAttribute("user", user); 
            destPage = "view/driver_s_assignments.jsp";  
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response); 
            break; 
        default:
            users = dao.getAllUsers();           
            request.setAttribute("users", users);                  
            destPage = "view/drivers.jsp";   
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            break;
        }      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
