package Servlets;

import Connexion.DatabaseConnection;
import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Brand;
import com.google.gson.Gson;
import model.Model;

/**
 *
 * @author akhaldi
 */


public class GetBrandModelServlet extends HttpServlet {
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
          
            Dao dao = new Dao(DatabaseConnection.getConnection());
            String op = request.getParameter("operation");
            
            if (op.equals("brand")) {
                List<Brand> brands = dao.getAllBrand();
                Gson json = new Gson();
                String brandList = json.toJson(brands);
                response.setContentType("text/html");
                response.getWriter().write(brandList);
            }

            if (op.equals("model")) {
                int id = Integer.parseInt(request.getParameter("id"));
                List<Model> models = dao.getModelByBrandId(id);
                Gson json = new Gson();
                String modelList = json.toJson(models);
                response.setContentType("text/html");
                response.getWriter().write(modelList);
            }
        }
    }
}
