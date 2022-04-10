package Reports;

import Connexion.DatabaseConnection;
import Servlets.AssignmentsServlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.jstl.core.Config;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class GeneratePDF {
    
     String fileName = "C:\\Users\\akhaldi\\Desktop\\web project\\STB_CARBURANT_web\\STB_CARBURANT\\src\\java\\Reports\\";
     String outFile = "C:\\Users\\akhaldi\\Desktop\\web project\\STB_CARBURANT_web\\STB_CARBURANT\\web\\reports\\";
    
    public boolean generateReport(int vehicleId, int userId,int id,  String path) throws UnsupportedEncodingException {
        try {
           String outFile1=outFile+"assignment"+id+".pdf";

            JasperReport jasperReport=JasperCompileManager.compileReport(getClass().getClassLoader().getResourceAsStream("assignment.jrxml"));           
            
            Map<String, Object> params = new HashMap<String, Object>();         
            params.put("vehicleId", vehicleId);
            params.put("userId", userId);            
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,DatabaseConnection.getConnection());
            File file = new File("D:\\STB_CARBURANT\\reports\\"+"assignment"+id+".pdf");
//            File file = new File(outFile+"assignment"+id+".pdf");
            OutputStream outputSteam = new FileOutputStream(file);
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);
        
          
//            String outFile2=outFile+"engagement"+id+".pdf";
//            jasperReport=JasperCompileManager.compileReport(path+"\\src\\java\\Reports\\engagement.jrxml");            
//            String
//            params = new HashMap<String, Object>();           
//            params.put("assignmentId", id);
//     
//         
//            jasperPrint = JasperFillManager.fillReport(jasperReport, params,DatabaseConnection.getConnection());
//            
//            file = new File(path+ "\\web\\reports\\engagement"+id+".pdf");
//             outputSteam = new FileOutputStream(file);
//            JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);
            
            try {
              TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(AssignmentsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return true;
            
        } catch (Exception ex) {
            Logger.getLogger(GeneratePDF.class.getName()).log(Level.SEVERE, null, ex);
            
            return false; 
        }
    }
}
