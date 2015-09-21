import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.awt.List;
import java.io.*;

public class test {
    private static final int String = 0;
	private static final int Set = 0;

	public static void main(String[] args) {
            
            //looping for faculty here
            String fac = "FTMK";
            faculty(fac);
            testing();
            viewChapter();
            viewBlock();
            viewCode();
        }
        
        public static void faculty(String fac){
            //------------------------- header--------------------
                System.out.println("Faculty : " + fac);
                System.out.println("==============\n");
                System.out.println("------------------------------------------------------------------");
                System.out.println("|| CODE\t\t|| DESCRIPTION\t\t\t|| TOTAL PATIENT||");
                System.out.println("------------------------------------------------------------------");
            //------------------------- end header--------------------
        }
        
        public static void testing(){
        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=1234";
            Connection conn = DriverManager.getConnection(connectionURL);
            
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
                    System.out.println("|| "+ icd10_code_result +"\t|| "+ icd10_desc +"||\t "+ PatientTotal +"\t||");
            }
                System.out.println("------------------------------------------------------------------\n");
            st.close();
        }
         catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
         }
    }
        
        public static void viewChapter() {
        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=1234";
            Connection conn = DriverManager.getConnection(connectionURL);
            
            System.out.println("Total Patient by Chapter :");
                
                ArrayList<String> list= new ArrayList<String>();
                String query = "select icd10_chapters.id, icd10_chapters.name, substring(lhr_diagnosis.DiagnosisCd,1,2) AS diag, count(*) AS total from icd10_chapters, lhr_diagnosis WHERE lhr_diagnosis.DiagnosisCd REGEXP '^[a-zA-Z0-9]+$'and substring(lhr_diagnosis.DiagnosisCd,1,2)=icd10_chapters.id and LOCATION_CODE= 'FTMK' group by substring(lhr_diagnosis.DiagnosisCd,1,2);";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                
                while (rs.next()) {
                	
                	String icd10_chapter_result = rs.getString("diag");
                	String total = rs.getString("total");
                        String icd10_chapter_name = rs.getString("name");
                    //System.out.format("%s, %s\n", icd10_chapter_result, total);
                    System.out.println("|| "+ icd10_chapter_result +"\t|| "+ icd10_chapter_name +"||\t "+ total +"\t||");
            }
       
            st.close();
        }
         catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
         }    
        }
        
        
        
        
        
        
        public static void viewBlock() {
        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=1234";
            Connection conn = DriverManager.getConnection(connectionURL);
            
            System.out.println("Total Patient by Block :");
                
                ArrayList<String> list= new ArrayList<String>();
                String query = "select icd10_blocks.id, icd10_blocks.id2, icd10_blocks.name, substring(lhr_diagnosis.DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis, icd10_blocks WHERE ((substring(lhr_diagnosis.DiagnosisCd,3,3)>= icd10_blocks.id) && (substring(lhr_diagnosis.DiagnosisCd,3,3)<= icd10_blocks.id2)) and lhr_diagnosis.DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' and LOCATION_CODE= 'FTMK' group by substring(lhr_diagnosis.DiagnosisCd,3,3)";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                
                while (rs.next()) {
                	
                	String icd10_block_result = rs.getString("diag");
                	String total = rs.getString("total");
                	String icd10_block_name = rs.getString("name");
                    //System.out.format("%s, %s\n", icd10_block_result, total);
                    System.out.println("|| "+ icd10_block_result +"\t|| "+ icd10_block_name +"||\t "+ total +"\t||");
            } 
            st.close();
        }
         catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
         }
        }
        
       
        
        
        
        public static void viewCode() {
        try {
            String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=1234";
            Connection conn = DriverManager.getConnection(connectionURL);
            
            System.out.println("Total Patient by Code :");
             
                ArrayList<String> list= new ArrayList<String>();
                String query = "select icd10_codes.icd10_code, icd10_codes.icd10_desc, lhr_diagnosis.DiagnosisCd as diag, COUNT(lhr_diagnosis.DiagnosisCd) as total from icd10_codes, lhr_diagnosis WHERE lhr_diagnosis.DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' and substring(lhr_diagnosis.DiagnosisCd, 6,3)=icd10_codes.icd10_code and LOCATION_CODE= 'FTMK' group by substring(lhr_diagnosis.DiagnosisCd, 6,3) Union all select 'SUM' lhr_diagnosis.DiagnosisCd, COUNT(lhr_diagnosis.DiagnosisCd) from lhr_Diagnosis;";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                
                while (rs.next()) {
                	
                	String icd10_code_result = rs.getString("DiagnosisCd");
                	String total = rs.getString("total");
                	String icd10_desc  = rs.getString("icd10_desc ");
                    //System.out.format("%s, %s\n", icd10_code_result, total);
                    System.out.println("|| "+ icd10_code_result +"\t|| "+ icd10_desc +"||\t "+ total +"\t||");
            }


                
            st.close();
        }
         catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
         }    
        }
}