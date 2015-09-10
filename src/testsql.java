//import java.io.PrintStream;
import java.sql.*;

public class testsql {
    public static void main(String[] args) {
        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=";
            Connection conn = DriverManager.getConnection(connectionURL);
            String query = "SELECT * FROM icd10_blocks";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String Id = rs.getString("Id");
                String id2 = rs.getString("id2");
                String name = rs.getString("name");
                int idc = rs.getInt("idc");
                System.out.format("%s, %s, %s, %s\n", Id, id2, idc, name);
            }
            st.close();
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}