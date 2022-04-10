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
@Path("Get_Vehicule")
public class Get_VehiculeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Get_VehiculeResource
     */
    public Get_VehiculeResource() {
    }

    /**
     * Retrieves representation of an instance of Generic.Get_VehiculeResource
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
                ResultSet rs=st.executeQuery("select [vh_id],[vh_md_id],[vh_fl_id],[vh_code],[vh_immatriculaton],[vh_creation_date],[vh_active]\n" +
" from VEHICLE \n" +
"inner join AFFECTATION on vh_id=af_vh_id\n" +
"where af_active=1\n" +
"and af_usr_id="+usr_id);
                JSONArray array= new JSONArray();
                while (rs.next()) {            
            JSONObject object = new JSONObject();
            object.put("vh_id", rs.getInt(1));
            object.put("vh_md_id", rs.getInt(2));
            object.put("vh_fl_id", rs.getInt(3));
            object.put("vh_code", rs.getString(4));
            object.put("vh_immatriculaton", rs.getString(5));
            object.put("vh_creation_date", rs.getString(6));
            object.put("vh_active", rs.getByte(7));
            

            array.put(object);
        }
        return array.toString();
    }

    /**
     * PUT method for updating or creating an instance of Get_VehiculeResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
