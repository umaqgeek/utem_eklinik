package select_by_faculty;

import java.sql.*;
import java.util.ArrayList;

public class Select_by_faculty
{
    public static void main(String[]args) throws SQLException
    {
        String connectionURL = "jdbc:mysql://127.0.0.1/dump?user=root&password=";
        Connection conn = DriverManager.getConnection(connectionURL);
        String query0 =" Select distinct location_code from lhr_signs";
        Statement st0=conn.createStatement();
        ResultSet rs0 = st0.executeQuery(query0);
        while (rs0.next())
        {
            String location_code = rs0.getString("Location_code");
            String[] Faculty = { location_code };
        {
for(int i=0; i<Faculty.length; i++)
{
    {
        try {
                //------------------------- header--------------------
                System.out.println("Faculty : " +Faculty[i]);
                System.out.println("==============\n");
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("||SIGNS CODE||\b\b||DESCRIPTION||\b\b||TOTAL PATIENT||");
                System.out.println("----------------------------------------------------------------------------------");
                
                //------------------------- end header-------------------
            String query1 = "select lhr_signs.Signs_code,Signs_desc,count(*) as Bilangan_pelajar from lhr_signs where LOCATION_CODE='" +Faculty[i]+"' group by Signs_desc ORDER BY `Signs_desc` ASC " ;
            Statement st= conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            while (rs.next()) {
                String signs_code = rs.getString("Signs_code");
                String Signs_Description = rs.getString("Signs_desc");
                String Bilangan_pelajar = rs.getString("Bilangan_pelajar");

               System.out.println("||"+ signs_code +"||\b\b||"+ Signs_Description +"||\b\b||"+ Bilangan_pelajar +"||");
                    //----------------testing
               System.out.println("----------------------------------------------------------------------------------");
            }
         try {
            String query2="select count(*)  Signs_desc from lhr_signs where location_code='" +Faculty[i]+"' group by Location_code" ;
            Statement st1=conn.createStatement();
            ResultSet rs2= st1.executeQuery(query2);
            while(rs2.next()) {
                String Total = rs2.getString("Signs_desc");
                System.out.println("Total patient from "+Faculty[i]+" = "+ Total +"\n\n");
            }
         }
         catch (Exception e)
         {
             System.err.println("Got an exception!  ");
             System.err.println(e.getMessage());
         }
        }
              catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
         }
            }
}
}
}
    }
}






