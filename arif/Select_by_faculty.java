package select_by_faculty;

import java.sql.*;
import java.util.ArrayList;

public class Select_by_faculty
{
    public static void main(String[]args) throws SQLException
    {
        String connectionURL = "jdbc:mysql://127.0.0.1/dump?user=root&password=";
        Connection myconn = DriverManager.getConnection(connectionURL);
        PreparedStatement myStmt = null;
        ResultSet myRs =null;
        String query0 =" Select distinct location_code from lhr_signs";
        Statement st0=myconn.createStatement();
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
            myStmt  = myconn.prepareStatement ("select lhr_signs.Signs_code,Signs_desc,count(*) as Bilangan_pelajar from lhr_signs where LOCATION_CODE=? group by Signs_desc ORDER BY `Signs_desc` ASC ") ;
            myStmt.setString (1,Faculty[i]);
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                String signs_code = myRs.getString("Signs_code");
                String Signs_Description = myRs.getString("Signs_desc");
                String Bilangan_pelajar = myRs.getString("Bilangan_pelajar");

               System.out.println("||"+ signs_code +"||\b\b||"+ Signs_Description +"||\b\b||"+ Bilangan_pelajar +"||");
                    //----------------testing
               System.out.println("----------------------------------------------------------------------------------");
            }
         try {
            myStmt = myconn.prepareStatement ("select count(*)  Signs_desc from lhr_signs where location_code=? group by Location_code") ;
            myStmt.setString (1,Faculty[i]);
            myRs = myStmt.executeQuery();
            while(myRs.next()) {
                String Total = myRs.getString("Signs_desc");
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






