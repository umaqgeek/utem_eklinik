//import java.io.PrintStream;
import java.sql.*;

public class faculty2 {
    public static void main(String[] args) {
        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=";
            Connection conn = DriverManager.getConnection(connectionURL);
            String query = "SELECT count(PMI_no) FROM servercis.lhr_diagnosis WHERE DiagnosisCd LIKE '%01%' AND DiagnosisCd LIKE '%A00%' AND DiagnosisCd LIKE '%A01%' AND LOCATION_CODE = 'FTMK';";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String NumberOfPatient = rs.getString("count(PMI_no)");
                //String id2 = rs.getString("id2");
                //String name = rs.getString("name");
                //int idc = rs.getInt("idc");
                System.out.format("%s\n", NumberOfPatient);
            }
            st.close();
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}