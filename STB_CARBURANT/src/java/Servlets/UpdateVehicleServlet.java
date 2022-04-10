package Servlets;

import Connexion.DatabaseConnection;
import dao.Dao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author akhaldi
 */
@WebServlet("/update_vehicle")
public class UpdateVehicleServlet extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
           
        String id = request.getParameter("id");
        String code = request.getParameter("code");
        String immatriculation = request.getParameter("immatriculation");
        String modele = request.getParameter("modele");
        String dateCreation = request.getParameter("dateCreation");
        String carburant = request.getParameter("carburant");
        String active=request.getParameter("active");
        String consommation=request.getParameter("consommation");
        String region=request.getParameter("region");
        
        if(active==null){
            active="0";
        }else{
            active="1";
        }
        
        Dao dao = new Dao(DatabaseConnection.getConnection());
        dao.updateVehicle(id, code, immatriculation, modele, dateCreation, carburant, active, consommation,region);
        
        VehiclesServlet vehiclesServlet=new VehiclesServlet();
        vehiclesServlet.doGet(request, response);
        
        }catch (Exception e) {
            e.printStackTrace();
        }
    
    }

}
