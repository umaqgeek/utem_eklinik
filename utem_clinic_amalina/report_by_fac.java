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

	public static void main(String[] args) throws SQLException {
		
        String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=";
        Connection conn = DriverManager.getConnection(connectionURL);
        Statement st = conn.createStatement(), st2 = conn.createStatement(), st3 = conn.createStatement();
        String query = null;

		
		try {


                
                ArrayList<String> list = new ArrayList<String>();
                query = "SELECT * FROM `icd10_chapters` ORDER BY `icd10_chapters`.`Id` ASC ;";
                
                ResultSet rs = st.executeQuery(query);
                
                while (rs.next()) {
                    list.add(rs.getString("id")); //assign mysql result to list
                    list.add(rs.getString("name")); //assign mysql result to list
                   // Integer icd10_id_result = rs.getInt("Id");
                    //String icd10_name_result = rs.getString("name");

                   // System.out.println("|| "+ icd10_id_result +"\t|| "+ icd10_name_result + "");
            }
               


                
                try {
                	

                    query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '01';";
                    ResultSet rs3 = st3.executeQuery(query);
                	String chapter1_total = null;
                    while (rs3.next()) {
                    	
                    	chapter1_total= rs3.getString("count");
                    }
                  
                    System.out.println("Total patient by chapter : \n");
            
					System.out.println(list.get(0) + "   " + list.get(1) + "   " + chapter1_total);

                    System.out.println("\n\tTotal Patient by Block :");

                        ArrayList<String> list_01 = new ArrayList<String>();
                        String query_01 = "SELECT id, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag";
                        ResultSet rs_01 = st.executeQuery(query_01);
                        
                        while (rs_01.next()) {
                            //list_01.add(rs_01.getString("diag")); //assign mysql result to list
                        	String icd10_block_id_result = rs_01.getString("id");
                        	String icd10_block_name_result = rs_01.getString("name");
                        	String total = rs_01.getString("total");
                            System.out.format("\t%s   %s   %s\n", icd10_block_id_result, icd10_block_name_result, total);
                            
                            String remove_last_char;
                            //System.out.println(icd10_block_id_result.substring(0, icd10_block_id_result.length()-1));
                            remove_last_char = icd10_block_id_result.substring(0, icd10_block_id_result.length()-1);
                            
                            System.out.println("\n\t\tTotal Patient by Code :");
                            ArrayList<String> list_01_code = new ArrayList<String>();
                            String query_01_code = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code  group by DiagnosisCd;";
                            //String query_01_code = "SELECT substring(DiagnosisCd,6,5) as diag, COUNT(DiagnosisCd) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"'  group by DiagnosisCd";
                            ResultSet rs_01_code = st2.executeQuery(query_01_code);
                            
                            while (rs_01_code.next()) {
                            	
                            	String icd10_code_01_result = rs_01_code.getString("icd10_code_strip");
                            	String icd10_code_desc_01_result = rs_01_code.getString("icd10_desc");
                            	String total_code_01 = rs_01_code.getString("total");
                                System.out.format("\t\t%s\t%s\t%s\n", icd10_code_01_result, icd10_code_desc_01_result, total_code_01);
                            }
                        }

                        //System.out.println(list_01);

                        
                  
                }
                 catch (Exception e) {
                    System.err.println("Got an exception! hh ");
                    System.err.println(e.getMessage());
                 }// try end
                
                
                System.out.println("------------------------------------------------------------------\n");
                
                System.out.println(list);
                System.out.println(list.get(1));
                

                
      
        }
         catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
         }
        
        
        
        
        conn.close();
        
        }
}