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
public class Report5 {
    
    public static void main(String[] args) {
        
        
        
        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=";
            Connection conn = DriverManager.getConnection(connectionURL);
            
                String a = "FTMK"; //--------------> (temporarily) will update base on user selected
                String faculty = a;
                //------------------------- header--------------------
                System.out.println("Faculty : " + faculty);
                System.out.println("==============\n");
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("|| CODE\t\t|| DESCRIPTION\t\t\t\t\t|| TOTAL PATIENT||");
                System.out.println("----------------------------------------------------------------------------------");
                //------------------------- end header--------------------
               
                
                ArrayList<String> list= new ArrayList<String>();
                String query = "SELECT Medication_code, Medication_desc, COUNT(*) as PatientTotal FROM servercis.lhr_medication WHERE LOCATION_CODE='FTMK' group by LOCATION_CODE, Medication_code ;";
                //String query = "SELECT LOCATION_CODE COUNT(*) as PatientTotal2 FROM servercis.lhr_medication WHERE LOCATION_CODE='FTMK' group by LOCATION_CODE;";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                int Total = 0;
                
                while (rs.next()) {
                    list.add(rs.getString("Medication_code")); //assign mysql result to list
                    //String LOCATION_CODE = rs.getString("LOCATION_CODE");
                    String Medication_code = rs.getString("Medication_code");
                    String Medication_desc = rs.getString("Medication_desc");
                    
                    String PatientTotal = rs.getString("PatientTotal");

                    //------------nak buat loop kat sini pecahkan base on chapter/block/code 3 level
                    System.out.println("|| "+ Medication_code +"\t|| "+ Medication_desc +"\t\t||\t "+ PatientTotal +"\t||");
                    //----------------testing
                    System.out.println("----------------------------------------------------------------------------------");
                    //Total += PatientTotal;
                    
                    //for(foo)
                    //----------------end testing
                    System.out.println("----------------------------------------------------------------------------------");
            }
                
                System.out.println("----------------------------------------------------------------------------------\n");
                
                //list.add(rs.getString("PatientTotal")); //assign mysql result to list
                //String PatientTotal = rs.getString("PatientTotal");
                //System.out.println("Total Patient by Chapter :"+PatientTotal+"");
                //System.out.println("Total Patient by Block :\n\n");
                
            st.close();
        }
         catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
         }
        }
    
    
    
}
