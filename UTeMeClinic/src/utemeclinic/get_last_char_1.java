import java.sql.*;
import java.io.*;

public class get_last_char_1 {
    public static void main(String[] args) {
        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=1234";
            Connection conn = DriverManager.getConnection(connectionURL);
            String query = "SELECT COUNT(DISTINCT icd10_code, icd10_chapter, icd10_block) AS TEST FROM icd10_codes;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //String icd10_code_result = rs.getString("icd10_code");
                //String icd10_chapter_result = rs.getString("icd10_chapter");
                //String icd10_block_result = rs.getString("icd10_block");
                int TEST = rs.getInt("TEST");
                //int idc = rs.getInt("idc");
                //System.out.format("%s, %s, %s\n", icd10_code_result.substring(5), icd10_chapter_result, icd10_block_result);
                System.out.format("%s\n", TEST);

                //System.out.println(icd10_code_result);
                //System.out.println(icd10_chapter_result);


                
            }
            st.close();
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
   
        //System.out.println("01A00A000".substring(5));
        //System.out.println("01A00A01".substring(5));        
    }
   
    
}