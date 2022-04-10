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
import model.Employee;

/**
 *
 * @author akhaldi
 */
@WebServlet("/add_driver")
public class AddDriverServlet extends HttpServlet {



   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String employeeId = request.getParameter("employee");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            
            Dao dao = new Dao(DatabaseConnection.getConnection());   
            Dao dao2 = new Dao(DatabaseConnection.getConnection2()); 
            
            if(dao.checkIfDriverExists(Integer.valueOf(employeeId))){
                
                response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Ce conducteur existe!');");                                
                pw.println("</script>");               
             
                List<Employee> employees = dao2.getAllEmployees();
                request.setAttribute("employees", employees);                       
            
                RequestDispatcher rd=request.getRequestDispatcher("view/add-driver.jsp");
                rd.include(request, response);
                
            }else{
                         
                Employee employee = dao2.getEmployeeById(Integer.valueOf(employeeId));          
                dao.addNewUser(employee, login, password);
                DriversServlet driversServlet=new DriversServlet();
                driversServlet.doGet(request, response);
            }           
      }catch (Exception e) {
            e.printStackTrace();
        }
    }

   
}
