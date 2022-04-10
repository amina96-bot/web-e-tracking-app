/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet("/update_driver")
public class UpdateDriverServlet extends HttpServlet {

 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try{
           
        String id = request.getParameter("id");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String active=request.getParameter("active");       
        
        if(active==null){
            active="0";
        }else{
            active="1";
        }
        
        Dao dao = new Dao(DatabaseConnection.getConnection());
        dao.updateUser(id, login, password, active);
        
        DriversServlet driversServlet=new DriversServlet();
        driversServlet.doGet(request, response);
        
        }catch (Exception e) {
            e.printStackTrace();
        }
    
    }

}
