import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.awt.List;
import java.io.*;

public class report_by_fac {
    private static final int String = 0;
	private static final int Set = 0;

	public static void main(String[] args) {
        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=1234";
            Connection conn = DriverManager.getConnection(connectionURL);
            
                String a = "FTMK"; //--------------> (temporarily) will update base on user selected
                String faculty = a;
                //------------------------- header--------------------
                System.out.println("Faculty : " + faculty);
                System.out.println("==============\n");
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("|| CODE\t\t|| DESCRIPTION\t\t\t\t\t|| TOTAL PATIENT||");
                System.out.println("----------------------------------------------------------------------------------");
                //------------------------- end header--------------------
               
                
                ArrayList<String> list= new ArrayList<String>();
                String query = "SELECT icd10_codes.icd10_code, icd10_codes.icd10_chapter, icd10_codes.icd10_block, icd10_codes.icd10_desc, lhr_diagnosis.DiagnosisCd, lhr_diagnosis.LOCATION_CODE, COUNT(*) AS PatientTotal FROM icd10_codes, lhr_diagnosis WHERE icd10_codes.icd10_code = lhr_diagnosis.DiagnosisCd and LOCATION_CODE= 'FTMK' GROUP BY icd10_code, LOCATION_CODE ORDER BY icd10_codes.icd10_code ASC;";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                
                while (rs.next()) {
                    list.add(rs.getString("icd10_chapter")); //assign mysql result to list
                    String icd10_chapter_result = rs.getString("icd10_chapter");
                    String icd10_block_result = rs.getString("icd10_block");
                    String icd10_code_result = rs.getString("icd10_code");
                    String icd10_desc = rs.getString("icd10_desc");
                    String DiagnosisCd = rs.getString("DiagnosisCd");
                    String LOCATION_CODE = rs.getString("LOCATION_CODE");
                    String PatientTotal = rs.getString("PatientTotal");

                    //------------nak buat loop kat sini pecahkan base on chapter/block/code 3 level
                    System.out.println("|| "+ icd10_code_result +"\t|| "+ icd10_desc +"\t\t||\t "+ PatientTotal +"\t||");
                    //----------------testing
                    System.out.println("----------------------------------------------------------------------------------");
                    System.out.println("code in string:" + icd10_code_result.substring(6));
                    int foo = Integer.parseInt(icd10_code_result.substring(6));
                    System.out.println("code in int:" +foo);
                    //for(foo)
                    //----------------end testing
                    System.out.println("----------------------------------------------------------------------------------");
            }
                
                System.out.println("----------------------------------------------------------------------------------\n");
                
                
                System.out.println("Total Patient by Chapter :");
                System.out.println("Total Patient by Block :\n\n");
                
            st.close();
        }
         catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
         }
        }
}