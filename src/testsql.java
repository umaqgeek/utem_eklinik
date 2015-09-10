import java.awt.event.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.*;

public class testsql
{
 
  public static void main(String[] args)
  {
    try
    {
      // create our mysql database connection
      //String myDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
      //String myUrl = "jdbc:mysql://localhost/servercis";
      //Class.forName(myDriver);
      //Connection conn = DriverManager.getConnection(myUrl, "root", "");
      //Class.forName("com.mysql.jdbc.Driver");
      String connectionURL="jdbc:mysql://127.0.0.1/servercis?" +
                                   "user=root&password=";
      Connection conn=DriverManager.getConnection(connectionURL);

       
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "SELECT * FROM icd10_blocks";
 
      // create the java statement
      Statement st = conn.createStatement();
       
      // execute the query, and get a java resultset
      ResultSet rs = st.executeQuery(query);
       
      // iterate through the java resultset
      while (rs.next())
      {
        //int id = rs.getInt("id");
        String Id = rs.getString("Id");
        String id2 = rs.getString("id2");          
        String name = rs.getString("name");
        int idc = rs.getInt("idc");
         
        // print the results
        System.out.format("%s, %s, %s, %s\n", Id, id2, idc, name);
      }
      st.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
  }
}
