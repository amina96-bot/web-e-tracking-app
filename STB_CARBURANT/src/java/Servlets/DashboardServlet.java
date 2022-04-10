package Servlets;

import Connexion.DatabaseConnection;
import dao.Dao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author akhaldi
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");    
        Dao dao = new Dao(DatabaseConnection.getConnection()); 
        
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
   
        RequestDispatcher dispatcher;
        String destPage = "";
         if(action==null){
            action="null";
        }
        switch (action) {
            case "null":
            request.setAttribute("countUnprocessedRequests", dao.countUnprocessedRequests(user.getEmployee().getRegion()));   
            request.setAttribute("countTotalUnprocessedRequests", dao.countTotalUnprocessedRequests());  
            request.setAttribute("countUnConfirmedRequests", dao.countUnConfirmedRequests());
            destPage = "view/dashboard.jsp";  
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            break; 
        case "new_requests_assignments_list" :
            if(user.getRoles().contains("administrateur")){
                request.setAttribute("assignments", dao.getAllNewRequestsAssignments());   
            }else{
                request.setAttribute("assignments", dao.getAllNewRequestsAssignmentsByRegion(user.getEmployee().getRegion()));   
            }                        
            destPage = "view/new_fuel_requests_assignments.jsp";  
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
