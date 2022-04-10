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
import javax.ws.rs.QueryParam;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author nbenadjimi
 */
@Path("Get_Affectation")
public class Get_AffectationResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Get_AffectationResource
     */
    public Get_AffectationResource() {
    }

    /**
     * Retrieves representation of an instance of Generic.Get_AffectationResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/query")
    @Produces("application/json")
    public String getJson(@QueryParam("usr_id") int usr_id) throws Exception{
        //TODO return proper representation object
        Connection connection; Statement st = null;
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                connection = DriverManager.getConnection("jdbc:sqlserver://"+new DatabaseConnection().getServer()+";database="+new DatabaseConnection().getDatabase()+";user="+new DatabaseConnection().getUser()+";password="+new DatabaseConnection().getPassword());
                st = connection.createStatement();
                ResultSet rs=st.executeQuery("SELECT [af_id],[af_usr_id],[af_vh_id],[af_date] ,[af_active]  FROM [NAFTAL].[dbo].[AFFECTATION] where [af_active]=1 and af_usr_id="+usr_id);
                JSONArray array= new JSONArray();
                while (rs.next()) {            
            JSONObject object = new JSONObject();
            object.put("af_id", rs.getInt(1));
            object.put("af_usr_id", rs.getInt(2));
            object.put("af_vh_id", rs.getInt(3));
            object.put("af_date", rs.getString(4));
            object.put("af_active", rs.getByte(5));
            

            array.put(object);
        }
        return array.toString();
    }

    /**
     * PUT method for updating or creating an instance of Get_AffectationResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
