import java.sql.*;
import java.io.*;

public class Select_by_faculty {
    public static void main(String[] args) {
        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/dump?user=root&password=";
            Connection conn = DriverManager.getConnection(connectionURL);
            String query1 = " select lhr_signs.Signs_code,Signs_desc,count(*) as Bilangan_pelajar from lhr_signs where LOCATION_CODE='FTMK' group by Signs_desc ORDER BY `Signs_desc` ASC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            while (rs.next()) {

                System.out.println("Signs_code, Signs_Description , Bilangan_pelajar");

                String signs_code = rs.getString("Signs_code");
                String Signs_Description = rs.getString("Signs_desc");
                String Bilangan_pelajar = rs.getString("Bilangan_pelajar");
               
               
                System.out.format("%s, %s,%s\n" ,signs_code,Signs_Description,Bilangan_pelajar);
            }
            st.close();
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/dump?user=root&password=";
            Connection conn = DriverManager.getConnection(connectionURL);

            String query=" select count(*) Signs_desc from lhr_signs where LOCATION_CODE='FTMK'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                String Total = rs.getString("Signs_desc");
                System.out.println("Total number of patient from FTMK:");
                System.out.format("%s\n",Total);
            }
            st.close();
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        
    }


}