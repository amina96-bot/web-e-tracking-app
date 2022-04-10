/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generic;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import java.sql.ResultSet;
import Connexion.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author nbenadjimi
 */
@Path("Get_Users")
public class Get_UsersResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Get_UsersResource
     */
    public Get_UsersResource() {
    }

    /**
     * Retrieves representation of an instance of Generic.Get_UsersResource
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
                ResultSet rs=st.executeQuery("SELECT [usr_id],[usr_name],[usr_first_name],[usr_dep_id],[usr_login],[usr_password],[usr_active]  FROM [NAFTAL].[dbo].[USERS] ");
                JSONArray array= new JSONArray();
                while (rs.next()) {            
            JSONObject object = new JSONObject();
            object.put("usr_id", rs.getInt(1));
            object.put("usr_name", rs.getString(2));
            object.put("usr_first_name", rs.getString(3));
            object.put("usr_dep_id", rs.getInt(4));
            object.put("usr_login", rs.getString(5));
            object.put("usr_password", rs.getString(6));
            object.put("usr_active", rs.getByte(7));
            array.put(object);
        }
        return array.toString();
    }

    /**
     * PUT method for updating or creating an instance of Get_UsersResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
