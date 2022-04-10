/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generic;

import Connexion.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author nbenadjimi
 */
@Path("Get_Departement")
public class Get_DepartementResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Get_DepartementResource
     */
    public Get_DepartementResource() {
    }

    /**
     * Retrieves representation of an instance of Generic.Get_DepartementResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() throws Exception{
        //TODO return proper representation object
        Connection connection; Statement st = null;
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                connection = DriverManager.getConnection("jdbc:sqlserver://"+new DatabaseConnection().getServer()+";database="+new DatabaseConnection().getDatabase()+";user="+new DatabaseConnection().getUser()+";password="+new DatabaseConnection().getPassword());
                st = connection.createStatement();
                ResultSet rs=st.executeQuery("SELECT [dep_id],[dep_description] FROM [NAFTAL].[dbo].[DEPARTEMENT]");
                JSONArray array= new JSONArray();
                while (rs.next()) {            
            JSONObject object = new JSONObject();
            object.put("dep_id", rs.getInt(1));
            object.put("dep_description", rs.getString(2));

            array.put(object);
        }
        return array.toString();
    }

    /**
     * PUT method for updating or creating an instance of Get_DepartementResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
