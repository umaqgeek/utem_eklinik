import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.awt.List;
import java.io.*;

public class report_2 {
    private static final int String = 0;
	private static final int Set = 0;

	public static void main(String[] args) throws SQLException {
		
	    String faculty;
        Integer tot_by_fac = null;
	    ResultSet rs;
	    ResultSet rs_block;
	    ResultSet rs_code;
	    String query = null;
	    String remove_last_char;
	    String connectionURL;
	    
	      
	    Scanner in = new Scanner(System.in);
	 
	    System.out.println("Enter a faculty : ");
	    faculty = in.nextLine();
	    System.out.println("You entered string "+faculty);
		
        connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=";
        Connection conn = DriverManager.getConnection(connectionURL);
        PreparedStatement st1 = conn.prepareStatement(connectionURL);

		
		try {
                
                ArrayList<String> chapter_list = new ArrayList<String>();
                query = "SELECT * FROM `icd10_chapters` ORDER BY `icd10_chapters`.`Id` ASC";              
                rs = st1.executeQuery(query);
                
                while (rs.next()) {
                	chapter_list.add(rs.getString("id")); //assign mysql result to list
                	chapter_list.add(rs.getString("name")); //assign mysql result to list
                   // Integer icd10_id_result = rs.getInt("Id");
                    //String icd10_name_result = rs.getString("name");

                   // System.out.println("|| "+ icd10_id_result +"\t|| "+ icd10_name_result + "");
                }
                
                query = "SELECT COUNT(*) AS tot_by_fac FROM `lhr_diagnosis` WHERE `lhr_diagnosis`.`LOCATION_CODE` = ?";              
                st1 = conn.prepareStatement(query);
                st1.setString(1, faculty);
                rs = st1.executeQuery();

                while (rs.next()) {
                	tot_by_fac = rs.getInt("tot_by_fac");
                }
                
				System.out.println("Total patient by faculty : " + tot_by_fac);
                System.out.println("Total patient by chapter : \n");
                
            		String chapter_total_result = null;
                    query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '01' AND LOCATION_CODE = ?";
                    
                    st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                    while (rs.next()) {
                    	chapter_total_result = rs.getString("count");
                    }
            
					System.out.println(chapter_list.get(0) + "   " + chapter_list.get(1) + "   " + chapter_total_result);

					if (!"0".equals(chapter_total_result)){	// check chapter_total_result. if != 0 enter loop
						
						System.out.println("\n\tTotal Patient by Block :");

                        query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc = '"+ chapter_list.get(0) +"'";
                        
                        st1 = conn.prepareStatement(query); //recreate statement
                        st1.setString(1, faculty); // set input parameter
                        rs_block = st1.executeQuery();
                        
                        while (rs_block.next()) {
                        	String block_id_result = rs_block.getString("id");
                        	String block_name_result = rs_block.getString("name");
                        	String block_total_result = rs_block.getString("total");
                            System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
                            

                            //System.out.println(icd10_block_id_result.substring(0, icd10_block_id_result.length()-1));
                            remove_last_char = block_id_result.substring(0, block_id_result.length()-1); //remove last character in 'id' resultset retrieve from icd10_blocks table. A00 = A0 

                            System.out.println("\n\t\tTotal Patient by Code :");
                            query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
                            //String query_01_code = "SELECT substring(DiagnosisCd,6,5) as diag, COUNT(DiagnosisCd) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"'  group by DiagnosisCd";
                            
                            st1 = conn.prepareStatement(query); //recreate statement
                            st1.setString(1, faculty); // set input parameter
                            rs_code = st1.executeQuery();
                            //rs_code = st2.executeQuery(query);
                            
                            while (rs_code.next()) {
                            	
                            	String code_strip_result = rs_code.getString("icd10_code_strip");
                            	String code_desc_result = rs_code.getString("icd10_desc");
                            	String code_total_result = rs_code.getString("total");
                                System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
                            }// code loop end
                        }// block loop end
					}// if bracket end

                
                System.out.println("------------------------------------------------------------------\n");
               
                
                //chapter 02
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '02' AND LOCATION_CODE = ?";
                st1 = conn.prepareStatement(query);
                st1.setString(1, faculty);
                rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(2) + "   " + chapter_list.get(3) + "   " + chapter_total_result);

				if (!"0".equals(chapter_total_result)){			    				    
					System.out.println("\n\tTotal Patient by Block :");
	
	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(2) +"'";
	                st1 = conn.prepareStatement(query);
	                st1.setString(1, faculty);
	                rs_block = st1.executeQuery();
                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query);
	                    st1.setString(1, faculty);
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end	
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                                
                //chapter 03
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '03' AND LOCATION_CODE = ?";
                st1 = conn.prepareStatement(query);
                st1.setString(1, faculty);
                rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(4) + "   " + chapter_list.get(5) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(4) +"'";
	                st1 = conn.prepareStatement(query);
	                st1.setString(1, faculty);
	                rs_block = st1.executeQuery();
                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query);
	                    st1.setString(1, faculty);
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                //chapter 04
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '04' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query);
                st1.setString(1, faculty);
                rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(6) + "   " + chapter_list.get(7) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(6) +"'";
	                st1 = conn.prepareStatement(query);
	                st1.setString(1, faculty);
	                rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query);
	                    st1.setString(1, faculty);
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end			
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                //chapter 05
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '05' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(8) + "   " + chapter_list.get(9) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(8) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                //chapter 06
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '06' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(10) + "   " + chapter_list.get(11) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(10) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end	
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                //chapter 07
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '07' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(12) + "   " + chapter_list.get(13) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(12) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end	
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                //chapter 08
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '08' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(14) + "   " + chapter_list.get(15) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(14) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end			
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                
                //chapter 09
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '09' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(16) + "   " + chapter_list.get(17) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(16) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end		
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                //chapter 10
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '10' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(18) + "   " + chapter_list.get(19) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(18) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end			
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                
                //chapter 11
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '11' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(20) + "   " + chapter_list.get(21) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(20) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end		
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                //chapter 12
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '12' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(22) + "   " + chapter_list.get(23) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(22) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end			
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                //chapter 13
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '13' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(24) + "   " + chapter_list.get(25) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(24) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end	
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                
                //chapter 14
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '14' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(26) + "   " + chapter_list.get(27) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(26) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end				
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                
                //chapter 15
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '15' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(28) + "   " + chapter_list.get(29) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(28) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end		
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                //chapter 16
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '16' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(30) + "   " + chapter_list.get(31) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(30) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end	
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                
                //chapter 17
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '17' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(32) + "   " + chapter_list.get(33) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(32) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end				
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                //chapter 18
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '18' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();
                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(34) + "   " + chapter_list.get(35) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(34) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end			
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                
                //chapter 19
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '19' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(36) + "   " + chapter_list.get(37) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(36) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end		
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                //chapter 20
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '20' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(38) + "   " + chapter_list.get(39) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(38) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end		
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                //chapter 21
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '21' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(40) + "   " + chapter_list.get(41) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(40) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end		
				}
				
                System.out.println("------------------------------------------------------------------\n");
                
                
                
                //chapter 22
                query = "select COUNT(DiagnosisCd) as COUNT from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,1,2) = '22' AND LOCATION_CODE = ?;";
                st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                while (rs.next()) {
                	chapter_total_result = rs.getString("count");
                }
                
				System.out.println(chapter_list.get(42) + "   " + chapter_list.get(43) + "   " + chapter_total_result);
				
				if (!"0".equals(chapter_total_result)){	
                    System.out.println("\n\tTotal Patient by Block :");

	                query = "SELECT id, idc, name, total FROM icd10_blocks, (select substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE icd10_blocks.id = diag AND idc ='"+ chapter_list.get(42) +"'";
	                st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_block = st1.executeQuery();
	                
	                while (rs_block.next()) {
	                	String block_id_result = rs_block.getString("id");
	                	String block_name_result = rs_block.getString("name");
	                	String block_total_result = rs_block.getString("total");
	                    System.out.format("\t%s   %s   %s\n", block_id_result, block_name_result, block_total_result);
	                    
	                    remove_last_char = block_id_result.substring(0, block_id_result.length()-1);
	                    
	                    System.out.println("\n\t\tTotal Patient by Code :");
	                    query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
	                    st1 = conn.prepareStatement(query); //recreate statement
	                    st1.setString(1, faculty); // set input parameter
	                    rs_code = st1.executeQuery();
	                    
	                    while (rs_code.next()) {
	                    	
	                    	String code_strip_result = rs_code.getString("icd10_code_strip");
	                    	String code_desc_result = rs_code.getString("icd10_desc");
	                    	String code_total_result = rs_code.getString("total");
	                        System.out.format("\t\t%s\t%s\t%s\n", code_strip_result, code_desc_result, code_total_result);
	                    }// code loop end
	                }// block loop end		
				}
				
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