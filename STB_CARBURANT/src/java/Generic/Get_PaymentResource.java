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
@Path("GetPayment")
public class Get_PaymentResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetPaymentResource
     */
    public Get_PaymentResource() {
    }

    /**
     * Retrieves representation of an instance of Generic.Get_PaymentResource
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
                ResultSet rs=st.executeQuery("select [pa_id]\n" +
"      ,[pa_usr_id]\n" +
"      ,[pa_date]\n" +
"      ,[pa_distance]\n" +
"      ,[pa_unit_price]\n" +
"      ,[pa_cost]\n" +
"      ,[pa_latitude]\n" +
"      ,[pa_longitude]\n" +
"      ,[pa_pt_id]\n" +
"      ,[pa_sync_id]\n" +
"      ,[pa_sync_date]\n" +
"      ,[pa_comment]\n" +
"      ,[pa_file_path]\n" +
"      ,[pa_file_name]\n" +
"      ,[pa_km_counter_file_path]\n" +
"      ,[pa_km_counter_file_name]"
                        + " from USERS \n" +
"inner join AFFECTATION on usr_id=af_usr_id \n" +
"inner join PAYMENT on pa_usr_id=usr_id \n" +
"where af_active=1 and  pa_date >=af_date and usr_id="+usr_id);
                JSONArray array= new JSONArray();
                while (rs.next()) {            
            JSONObject object = new JSONObject();
object.put("pa_id", rs.getInt(1));
object.put("pa_usr_id", rs.getInt(2));
object.put("pa_date", rs.getString(3));
object.put("pa_distance", rs.getDouble(4));
object.put("pa_unit_price", rs.getDouble(5));
object.put("pa_cost", rs.getDouble(6));
object.put("pa_latitude", rs.getDouble(7));
object.put("pa_longitude", rs.getDouble(8));
object.put("pa_pt_id", rs.getInt(9));
object.put("pa_sync_id", rs.getInt(10));
object.put("pa_sync_date", rs.getString(11));
object.put("pa_comment", rs.getString(12));
object.put("pa_file_path", rs.getString(13));
object.put("pa_file_name", rs.getString(14));
object.put("pa_km_counter_file_path", rs.getString(15));
object.put("pa_km_counter_file_name", rs.getString(16));

            array.put(object);
        }
        return array.toString();
    }

    /**
     * PUT method for updating or creating an instance of Get_PaymentResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
