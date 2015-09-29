import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.awt.List;
import java.io.*;

public class report_2 {
    private static final int String = 0;
	private static final int Set = 0;

	public static void main(String[] args) throws SQLException {
		
        String connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=1234";
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
               
                
                //chapter 02
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '02';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(2) + "   " + chapter_list.get(3) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(2) +"'";
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
                
                
                
                //chapter 03
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '03';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(4) + "   " + chapter_list.get(5) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(4) +"'";
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
                
                
                
                //chapter 04
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '04';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(6) + "   " + chapter_list.get(7) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(6) +"'";
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
                
                
                //chapter 05
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '05';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(8) + "   " + chapter_list.get(9) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(8) +"'";
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
                
                
                
                //chapter 06
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '06';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(10) + "   " + chapter_list.get(11) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(10) +"'";
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
                
                
                
                //chapter 07
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '07';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(12) + "   " + chapter_list.get(13) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(12) +"'";
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
                
                
                
                //chapter 08
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '08';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(14) + "   " + chapter_list.get(15) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(14) +"'";
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
                
                
                
                
                //chapter 09
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '09';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(16) + "   " + chapter_list.get(17) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(16) +"'";
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
                
                
                
                
                //chapter 10
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '10';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(18) + "   " + chapter_list.get(19) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(18) +"'";
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
                
                
                
                
                //chapter 11
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '11';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(20) + "   " + chapter_list.get(21) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(20) +"'";
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
                
                
                
                //chapter 12
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '12';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(22) + "   " + chapter_list.get(23) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(22) +"'";
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
                
                
                
                //chapter 13
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '13';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(24) + "   " + chapter_list.get(25) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(24) +"'";
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
                
                
                
                
                //chapter 14
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '14';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(26) + "   " + chapter_list.get(27) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(26) +"'";
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
                
                
                
                
                //chapter 15
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '15';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(28) + "   " + chapter_list.get(29) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(28) +"'";
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
                
                
                //chapter 16
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '16';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(30) + "   " + chapter_list.get(31) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(30) +"'";
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
                
                
                
                
                //chapter 17
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '17';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(32) + "   " + chapter_list.get(33) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(32) +"'";
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
                
                
                
                
                //chapter 19
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '19';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(36) + "   " + chapter_list.get(37) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(36) +"'";
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
                
                
                
                //chapter 20
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '20';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(38) + "   " + chapter_list.get(39) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(38) +"'";
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
                
                
                
                //chapter 21
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '21';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(40) + "   " + chapter_list.get(41) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(40) +"'";
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
                
                
                
                //chapter 22
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '22';";
                rs = st1.executeQuery(query);

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(42) + "   " + chapter_list.get(43) + "   " + chapter_total_result);
				
                System.out.println("\n\tTotal Patient by Block :");

                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(42) +"'";
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