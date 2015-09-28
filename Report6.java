/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.awt.List;
import java.io.*;
/**
 *
 * @author adam
 */
public class Report6 {
    
    public static void main(String[] args) {
        
        String[] Faculty={"FTMK","FKE","FKEKK","FKP","FKM","FTK","FPTT"};//--------------> (temporarily) will update base on user selected
        //int i;
        int i = 0;
        //int Total = 0;
        
        
        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=";
            
            
            Connection conn = DriverManager.getConnection(connectionURL);
                   ////////////
                //for (i=0; i<8; i++){
                while (i<8){
                    int Total = 0;
                    
                //String f = Faculty[i];
                ArrayList<String> list= new ArrayList<String>();
                //String query = "SELECT Medication_code, Medication_desc, COUNT(*) as PatientTotal FROM servercis.lhr_medication WHERE LOCATION_CODE='FTMK' group by LOCATION_CODE, Medication_code ;";
                //String query = "SELECT Medication_code, Medication_desc, COUNT(*) as PatientTotal FROM servercis.lhr_medication WHERE LOCATION_CODE='"+ Faculty[i] +"' group by Medication_code ;";
                String query = "SELECT LOCATION_CODE, Medication_code, Medication_desc, COUNT(Medication_code) as PatientTotal FROM servercis.lhr_medication WHERE LOCATION_CODE='"+ Faculty[i] +"' group by Medication_code;";
                
                //Try to implement PreparedStatement in order to manipulate sql query "WHERE=? to classify each faculty"
                //PreparedStatement statement = connection.prepareStatement("SELECT LOCATION_CODE, Medication_code, Medication_desc, COUNT(Medication_code) as PatientTotal FROM servercis.lhr_medication WHERE LOCATION_CODE=? group by LOCATION_CODE, Medication_code;");
                //End try(not working yet)
                
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                

                
                
                //------------------------- header--------------------
                System.out.println("Faculty : " + Faculty[i]);
                System.out.println("==============\n");
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("|| CODE\t\t|| DESCRIPTION\t\t\t\t\t|| TOTAL PATIENT||");
                System.out.println("----------------------------------------------------------------------------------");
                //------------------------- end header--------------------
                
                while (rs.next()) {
                    list.add(rs.getString("Medication_code")); //assign mysql result to list
                    
                    String Medication_code = rs.getString("Medication_code");
                    String Medication_desc = rs.getString("Medication_desc");
                    
                    String PatientTotal = rs.getString("PatientTotal");

                    //------------nak buat loop kat sini pecahkan base on code
                    System.out.println("|| "+ Medication_code +"\t|| "+ Medication_desc +"\t\t||\t "+ PatientTotal +"\t||");
                    
                    int PT = Integer.parseInt(PatientTotal);
                    Total += PT;
                    //----------------testing
                    System.out.println("----------------------------------------------------------------------------------");
                    //Total += PatientTotal;
                    
                    //for(foo)
                    //----------------end testing
                    System.out.println("----------------------------------------------------------------------------------");
            }
                
                System.out.println("----------------------------------------------------------------------------------\n");
                
                
                System.out.println("Faculty : " + Faculty[i]);
                System.out.println("Total : " + Total);
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("----------------------------------------------------------------------------------");
                i++;
                
     ///////////
                st.close();
                }
                
            //!!//st.close();
            
    ////////////
        }
         catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
         }
        }
    
    
    
}
