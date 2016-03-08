package eklinik_bill;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package testconnectionrmi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        //String host = "biocore-stag.utem.edu.my"; //server stagging
        String host = "biocore-devp.utem.edu.my"; // server development
        int port = 1099; // for now, stick to this port

        // example select query
        //String sql = "DESCRIBE pms_patient_biodata";
       // String sql = "SELECT COUNT(*) FROM EHR_CENTRAL";
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //2015-01-06 
        Date date = new Date();
        //System.out.println(dateFormat.format(date));
        String sql = "SELECT DISTINCT "
                    + "pb.PATIENT_NAME,  pb.HOME_ADDRESS, pb.NEW_IC_NO, pb.ID_NO, pb.MOBILE_PHONE, "
                    + "pdd.DRUG_ITEM_CODE, mdc.D_TRADE_NAME, pdd.DISPENSED_QTY, mdc.D_PRICE_PPACK, pdd.DISPENSED_QTY * mdc.D_PRICE_PPACK AS TOTAL "
                    + "FROM ehr_central ec "
                    + "INNER JOIN pms_patient_biodata pb "
                    + "ON ec.PMI_NO = pb.PMI_NO "
                    + "INNER JOIN pis_order_master pom "
                    + "ON ec.PMI_NO = pom.PMI_NO "
                    + "INNER JOIN pis_dispense_master pdm "
                    + "ON pom.ORDER_NO = pdm.ORDER_NO "
                    + "INNER JOIN pis_dispense_detail pdd "
                    + "ON pdm.ORDER_NO = pdd.ORDER_NO "
                    + "INNER JOIN pis_mdc2 mdc "
                    + "ON pdd.DRUG_ITEM_CODE = mdc.UD_MDC_CODE "
                    + "WHERE (ec.status = 1 OR ec.status = 3) "
                    + "AND ec.PMI_NO = '8910310652139' "
                    + "AND substring(pom.EPISODE_CODE,1,10)='2015-12-21' "
                    + "AND substring(pdm.ORDER_DATE,1,10)='2015-12-21'";
        
                    //+ "AND substring(ec.C_TXNDATE,1,10)='"+ dateFormat.format(date) +"'";
                   // + "AND substring(ec.C_TXNDATE,1,10)='2015-12-21'";
//        String sql ="SELECT DISTINCT "
//                    + "pdd.DRUG_ITEM_CODE, mdc.D_TRADE_NAME, pdd.DISPENSED_QTY, mdc.D_PRICE_PPACK, pdd.DISPENSED_QTY * mdc.D_PRICE_PPACK AS TOTAL, pom.EPISODE_CODE, pdm.ORDER_DATE, pdd.ORDER_NO,pdm.ORDER_NO,pom.ORDER_NO "
//                    + "FROM ehr_central ec "
//                    + "INNER JOIN pis_order_master pom "
//                    + "ON ec.PMI_NO = pom.PMI_NO "
//                    + "INNER JOIN pis_dispense_master pdm "
//                    + "ON pom.ORDER_NO = pdm.ORDER_NO "
//                    + "INNER JOIN pis_dispense_detail pdd "
//                    + "ON pdm.ORDER_NO = pdd.ORDER_NO "
//                    + "INNER JOIN pis_mdc2 mdc "
//                    + "ON pdd.DRUG_ITEM_CODE = mdc.UD_MDC_CODE "
//                    + "WHERE ec.PMI_NO ='8910310652139' "; //8910310652139
//                + "AND substring(pos.EPISODE_CODE,1,10)='"+ dateFormat.format(date) +"' "
//                + "AND substring(pdm.ORDER_DATE,1,10)='"+ dateFormat.format(date) +"'";
               // + "AND substring(pos.EPISODE_CODE,1,10)='2015-12-21' "
                //+ "AND substring(pdm.ORDER_DATE,1,10)='2015-12-21'";
//        String sql = "SELECT "
//                + "pe.PMI_NO, pe.NEW_IC_NO, pe.NAME, pe.STATUS, pb.HOME_ADDRESS, pb.MOBILE_PHONE "
//                + "FROM pms_episode pe "
//                + "INNER JOIN pms_patient_biodata pb "
//                + "ON pe.PMI_NO = pb.PMI_NO "//
//                + "WHERE pe.STATUS='Discharge' "
//                + "AND pe.EPISODE_DATE='03/12/2015'";
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
                
//                int grandtotal =0;
//                int total = Integer.parseInt(data.get(i).get(9));
//                grandtotal = grandtotal+ total;
//                System.out.println(grandtotal);
                System.out.print(data.get(i)); //number of print and loop based on size 
               // System.out.print(data.get(i).get(j) + " | "); //number of print and loop based on size 

            }
            System.out.println();
        }
    }

}
