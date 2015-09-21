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
        PreparedStatement st1 = conn.prepareStatement(connectionURL), st2 = conn.prepareStatement(connectionURL), st3 = conn.prepareStatement(connectionURL);
        ResultSet rs;
        ResultSet rs_block;
        ResultSet rs_code;
        String query = null;
        String remove_last_char;

		
		try {
                
                ArrayList<String> chapter_list = new ArrayList<String>();
                query = "SELECT * FROM `icd10_chapters` ORDER BY `icd10_chapters`.`Id` ASC ;";              
                rs = st1.executeQuery(query);
                
                while (rs.next()) {
                	chapter_list.add(rs.getString("id")); //assign mysql result to list
                	chapter_list.add(rs.getString("name")); //assign mysql result to list
                   // Integer icd10_id_result = rs.getInt("Id");
                    //String icd10_name_result = rs.getString("name");

                   // System.out.println("|| "+ icd10_id_result +"\t|| "+ icd10_name_result + "");
                }
                
            		String chapter_total_result = null;
                    query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '01';";
                    rs = st1.executeQuery(query);

                    while (rs.next()) {
                    	chapter_total_result = rs.getString("count");
                    }
                  
                    System.out.println("Total patient by chapter : \n");
            
					System.out.println(chapter_list.get(0) + "   " + chapter_list.get(1) + "   " + chapter_total_result);

                    System.out.println("\n\tTotal Patient by Block :");

                        query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(0) +"'";
                        rs_block = st1.executeQuery(query);
                        
                        while (rs_block.next()) {
                        	String block_id_result = rs_block.getString("id");
                        	String block_name_result = rs_block.getString("name");
                        	String block_total_result = rs_block.getString("total");
                            System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
                            

                            //System.out.println(icd10_block_id_result.substring(0, icd10_block_id_result.length()-1));
                            remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
                            
                            System.out.println("\n\t\tTotal Patient by Code :");
                            query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code  group by DiagnosisCd;";
                            //String query_01_code = "SELECT substring(DiagnosisCd,6,5) as diag, COUNT(DiagnosisCd) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"'  group by DiagnosisCd";
                            rs_code = st2.executeQuery(query);
                            
                            while (rs_code.next()) {
                            	
                            	String code_strip_result = rs_code.getString("icd10_code_strip");
                            	String code_desc_result = rs_code.getString("icd10_desc");
                            	String code_total_result = rs_code.getString("total");
                                System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
                            }// code loop end
                        }// block loop end

                
                System.out.println("------------------------------------------------------------------\n");
               
                
                //chapter 18
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '18';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(34) + "   " + chapter_list.get(35) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(34) +"'";
                rs_block = st1.executeQuery(query);
                
                while (rs_block.next()) {
                	String block_id_result = rs_block.getString("id");
                	String block_name_result = rs_block.getString("name");
                	String block_total_result = rs_block.getString("total");
                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
                    
                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
                    
                    System.out.println("\n\t\tTotal Patient by Code :");
                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code  group by DiagnosisCd;";
                    rs_code = st2.executeQuery(query);
                    
                    while (rs_code.next()) {
                    	
                    	String code_strip_result = rs_code.getString("icd10_code_strip");
                    	String code_desc_result = rs_code.getString("icd10_desc");
                    	String code_total_result = rs_code.getString("total");
                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
                    }// code loop end
                }// block loop end				
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                
                System.out.println(chapter_list);
                System.out.println(chapter_list.get(1));
      
      
        }
         catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
         }
        
        
        
        
        conn.close();
        
        }
}