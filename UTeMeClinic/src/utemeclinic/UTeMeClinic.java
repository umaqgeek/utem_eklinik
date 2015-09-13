/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utemeclinic;

/**
 *
 * @author Amalina
 */
//import java.io.PrintStream;
import java.sql.*;
public class UTeMeClinic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String connectionURL = "jdbc:mysql://10.73.32.200/servercis?user=root&password=qwerty";
            Connection conn = DriverManager.getConnection(connectionURL);
            //String query = "SELECT * FROM icd10_blocks";
            String query = "SELECT count(PMI_no) AS NumberOfPatient FROM lhr_diagnosis WHERE DiagnosisCd LIKE '%01%' AND DiagnosisCd LIKE '%A00%' AND DiagnosisCd LIKE '%A01%' AND LOCATION_CODE = 'FTMK';";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int NumberOfPatient = rs.getInt("NumberOfPatient");
                //String id2 = rs.getString("id2");
                //String name = rs.getString("name");
                //int idc = rs.getInt("idc");
                System.out.format("%s", NumberOfPatient);
            }
            st.close();
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
}
