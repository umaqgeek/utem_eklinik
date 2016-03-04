package report;

import java.sql.*;
import java.util.ArrayList;
/*
 * @author adam
 */

public class Report6 {

    public static void main(String[] args) {

        String[] Faculty = new String[]{"FTMK", "FKE", "FKEKK", "FKP", "FKM", "FTK", "FPTT"};//--------------> (temporarily) will update base on user selected
        int i = 0;

        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=";

            Connection conn = DriverManager.getConnection(connectionURL);
            PreparedStatement st1 = conn.prepareStatement(connectionURL);
            ArrayList<String> list = new ArrayList<String>();
            PreparedStatement statement = conn.prepareStatement("SELECT LOCATION_CODE, Medication_code, Medication_desc, COUNT(Medication_code) as PTotal FROM servercis.lhr_medication WHERE LOCATION_CODE=? group by Medication_code ;");

            Statement st = conn.createStatement();
           
            for (String LOCATION_CODE : Faculty) {
                statement.setString(1, LOCATION_CODE);
                ResultSet rs = statement.executeQuery();

                //------------------------- header--------------------
                System.out.println("\n==============");
                System.out.println("Faculty : " + Faculty[i]);
                System.out.println("==============");
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("|| CODE\t\t|| DESCRIPTION\t\t\t\t\t|| TOTAL ||");
                System.out.println("----------------------------------------------------------------------------------");
                //------------------------- end header--------------------
                int Total = 0;

                while (rs.next()) {
                    list.add(rs.getString("Medication_code")); //assign mysql result to list

                    String Medication_code = rs.getString("Medication_code");
                    String Medication_desc = rs.getString("Medication_desc");

                    String PTotal = rs.getString("PTotal");

                    //------------nak buat loop kat sini pecahkan base on code
                    System.out.println("|| " + Medication_code + "\t|| " + Medication_desc + "\t\t||\t " + PTotal + "\t||");

                    int PT = Integer.parseInt(PTotal);
                    Total += PT;
                    
                    System.out.println("----------------------------------------------------------------------------------");
                }

                System.out.println("----------------------------------------------------------------------------------");

                System.out.println("Faculty : " + Faculty[i]);
                System.out.println("Total : " + Total);
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("----------------------------------------------------------------------------------");
                i++;

                st.close();
                
                statement.clearParameters();
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

}
