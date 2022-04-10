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
import model.User;
import model.Vehicle;

@WebServlet("/add_assignment")
public class AddAssignmentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{          
            String user = request.getParameter("user");
            String vehicle = request.getParameter("vehicle");
            String affectationDate = request.getParameter("affectationDate");         
            Dao dao = new Dao(DatabaseConnection.getConnection());    
            
            if(dao.checkIfVehicleIsAffected(Integer.valueOf(vehicle)) || dao.checkAffectationDate(Integer.valueOf(user), Integer.valueOf(vehicle), affectationDate) ||  dao.checkIfUserHasActiveAffectation(Integer.valueOf(user))){                
                response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");            
                if(dao.checkIfVehicleIsAffected(Integer.valueOf(vehicle))){               
                    pw.println("alert('Affectation impossible [véhicule déjà affecté]');");
                }               
                 if(dao.checkIfUserHasActiveAffectation(Integer.valueOf(user))){               
                    pw.println("alert('Affectation impossible [Ce conducteur possède déjà un véhicule]');");
                }               
                if(dao.checkAffectationDate(Integer.valueOf(user), Integer.valueOf(vehicle), affectationDate)){
                    pw.println("alert('Affectation impossible [Ce véhicule est déjà affecté à cet utilisateur!]');");
                }               
                pw.println("history.back();"); 
                pw.println("</script>");                
                List<User> users = dao.getAllUsers();
                request.setAttribute("users", users);
                List<Vehicle> vehicles = dao.getAllVehicles();
                request.setAttribute("vehicles", vehicles);           
                RequestDispatcher rd=request.getRequestDispatcher("view/add-assignment.jsp");
                rd.include(request, response);
            }else{
                dao.addNewAssignment(user, vehicle, affectationDate);
                AssignmentsServlet assignmentsServlet=new AssignmentsServlet();
                assignmentsServlet.doGet(request, response);
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
