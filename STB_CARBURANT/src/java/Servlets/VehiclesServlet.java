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
import model.Fuel;
import model.User;
import model.Vehicle;

/**
 *
 * @author akhaldi
 */
@WebServlet("/vehicles")
public class VehiclesServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 

        String action = request.getParameter("action");
        if(action==null){
            action="list";
        }
        String id;    
        Dao dao = new Dao(DatabaseConnection.getConnection());
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");
        session.setAttribute("user", user);       
   
        String destPage = "";
        
        switch (action) {
        case "list" :
             List<Vehicle> vehicles;
             if(user.getRoles().contains("administrateur")){
                vehicles=dao.getAllVehicles();
            }else{
                vehicles=dao.getAllVehiclesByRegion(user.getEmployee().getRegion());
            }              
            request.setAttribute("vehicles", vehicles);                         
            destPage = "view/vehicles.jsp"; 
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);   
            break;
        case "add":
            List<Fuel> fuels = dao.getAllFuel();
            request.setAttribute("fuels", fuels);
            destPage = "view/add-vehicle.jsp";
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            break;
        case "update":
            id = request.getParameter("id");
            Vehicle vehicle=dao.getVehicleById(Integer.valueOf(id));
            vehicle.setCreationDate(vehicle.getCreationDate().substring(0, 10)+"T"+vehicle.getCreationDate().substring(11, 16));            
            fuels = dao.getAllFuel();
            request.setAttribute("fuels", fuels);
            request.setAttribute("vehicle", vehicle);          
            destPage = "view/update-vehicle.jsp";
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            break;
        case "delete":
            id = request.getParameter("id");
          
            if(dao.checkIfVehcileIsAffected(id)){
                 response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Impossible de supprimer ce véhicule! ');");  
                pw.println("history.back();");  
                pw.println("</script>"); 
            }else{
                dao.deleteVehicle(id);
                vehicles = dao.getAllVehicles();            
                request.setAttribute("vehicles", vehicles);            
                destPage = "view/vehicles.jsp";
                dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);
            }        
            break;
            
            case "vehicle_assignments":
            id = request.getParameter("id");
            vehicle=dao.getVehicleById(Integer.valueOf(id));
            List<Assignment> assignments = dao.getVehicleAssignments(id);

            request.setAttribute("assignments", assignments); 
            request.setAttribute("vehicle", vehicle); 
            destPage = "view/vehicle_s_assignments.jsp";  
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
