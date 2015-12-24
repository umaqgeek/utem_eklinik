package eklinik;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package testconnectionrmi;

import java.util.ArrayList;
import java.util.HashMap;
import main.RMIConnector;

/**
 *
 * @author umarmukhtar
 */
public class TestConnectionRMI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // call library
        RMIConnector rc = new RMIConnector();
        
        // declaration host and port
        String host = "biocore-stag.utem.edu.my";
        int port = 1099; // for now, stick to this port
        
        // example select query
        //String sql = "DESCRIBE lhr_diagnosis";
        String sql = "SELECT * from lhr_diagnosis";
        //String sql = "SELECT ld.diagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, ib.Id AS icd10_block, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_blocks ib, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,3,3) ='A65' AND ib.Id = 'A65' AND ic.icd10_code = ld.DiagnosisCd AND (ld.Episode_date BETWEEN '2015-01-01' AND '2015-11-21') group by DiagnosisCd";

        // execute query
        ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);
        

        
System.out.println(data); //number of print and loop based on size 

        // view all data and results
        for (int i = 0; i < data.size(); i++) {
            
            //System.out.println(data.get(i));
            //System.out.print(data.get(i).get(1));

            
            //System.out.print("Row #"+(i+1)+": ");
            for (int j = 0; j < data.get(i).size(); j++) {
                
                //System.out.println(data.get(i).size());
                //System.out.print(data.get(i)); //number of print and loop based on size 
                System.out.print(data.get(i).get(j) + " | "); //number of print and loop based on size 
                
            }
            System.out.println();
        }
    }
    
}
