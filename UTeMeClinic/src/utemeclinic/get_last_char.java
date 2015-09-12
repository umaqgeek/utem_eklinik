import java.sql.*;
import java.io.*;

public class get_last_char {
    public static void main(String[] args) {
        try {
            String connectionURL = "jdbc:mysql://10.73.32.200/servercis?user=root&password=qwerty";
            Connection conn = DriverManager.getConnection(connectionURL);
            String query = "SELECT icd10_codes.icd10_code, icd10_codes.icd10_chapter, icd10_codes.icd10_block, lhr_diagnosis.DiagnosisCd, lhr_diagnosis.LOCATION_CODE, COUNT(*) AS PatientTotal FROM icd10_codes, lhr_diagnosis WHERE icd10_codes.icd10_code = lhr_diagnosis.DiagnosisCd GROUP BY icd10_code, LOCATION_CODE;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String icd10_chapter_result = rs.getString("icd10_chapter");
                String icd10_block_result = rs.getString("icd10_block");
                String icd10_code_result = rs.getString("icd10_code");
                String DiagnosisCd = rs.getString("DiagnosisCd");
                String LOCATION_CODE = rs.getString("LOCATION_CODE");
                String PatientTotal = rs.getString("PatientTotal");
                //int idc = rs.getInt("idc");
                System.out.format("%s, %s, %s, %s, %s, %s\n", icd10_chapter_result, icd10_block_result, icd10_code_result.substring(5), DiagnosisCd, LOCATION_CODE, PatientTotal);
                //System.out.format("%s", Integer.valueOf(String.valueOf(icd10_chapter_result) + String.valueOf(icd10_block_result) +  String.valueOf(icd10_code_result.substring(5))));
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