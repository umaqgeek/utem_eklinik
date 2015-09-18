package Select_by_faculty;

import java.sql.*;
import java.io.*;

public class Select_by_faculty {
    public static void main(String[] args) {
        try {
              String a = "FTMK"; //--------------> (temporarily) will update base on user selected
                String faculty = a;
                //------------------------- header--------------------
                System.out.println("Faculty : " + faculty);
                System.out.println("==============\n");
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("||SIGNS CODE||\b\b||DESCRIPTION||\b\b||TOTAL PATIENT||");
                System.out.println("----------------------------------------------------------------------------------");
                //------------------------- end header--------------------
            String connectionURL = "jdbc:mysql://127.0.0.1/dump?user=root&password=";
            Connection conn = DriverManager.getConnection(connectionURL);
            String query1 = " select lhr_signs.Signs_code,Signs_desc,count(*) as Bilangan_pelajar from lhr_signs where LOCATION_CODE='FTMK' group by Signs_desc ORDER BY `Signs_desc` ASC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            while (rs.next()) {

               

                String signs_code = rs.getString("Signs_code");
                String Signs_Description = rs.getString("Signs_desc");
                String Bilangan_pelajar = rs.getString("Bilangan_pelajar");
               
               System.out.println("||"+ signs_code +"||\b\b||"+ Signs_Description +"||\b\b||"+ Bilangan_pelajar +"||");
                    //----------------testing
               System.out.println("----------------------------------------------------------------------------------");

                
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
                System.out.format("%s\n\n\n",Total);
            }
            st.close();
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        try {
              String a = "FKEKK"; //--------------> (temporarily) will update base on user selected
                String faculty = a;
                //------------------------- header--------------------
                System.out.println("Faculty : " + faculty);
                System.out.println("==============\n");
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("||SIGNS CODE||\b\b||DESCRIPTION||\b\b||TOTAL PATIENT||");
                System.out.println("----------------------------------------------------------------------------------");
                //------------------------- end header--------------------
            String connectionURL = "jdbc:mysql://127.0.0.1/dump?user=root&password=";
            Connection conn = DriverManager.getConnection(connectionURL);
            String query1 = " select lhr_signs.Signs_code,Signs_desc,count(*) as Bilangan_pelajar from lhr_signs where LOCATION_CODE='FKEKK' group by Signs_desc ORDER BY `Signs_desc` ASC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            while (rs.next()) {



                String signs_code = rs.getString("Signs_code");
                String Signs_Description = rs.getString("Signs_desc");
                String Bilangan_pelajar = rs.getString("Bilangan_pelajar");

               System.out.println("||"+ signs_code +"||\b\b||"+ Signs_Description +"||\b\b||"+ Bilangan_pelajar +"||");
                    //----------------testing
               System.out.println("----------------------------------------------------------------------------------");


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

            String query=" select count(*) Signs_desc from lhr_signs where LOCATION_CODE='FKEKK'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                String Total = rs.getString("Signs_desc");
                System.out.println("Total number of patient from FKEKK:");
                System.out.format("%s\n\n\n",Total);
            }
            st.close();
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }


}