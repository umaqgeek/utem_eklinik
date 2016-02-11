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
        //String sql = "SELECT COUNT(*) FROM EHR_CENTRAL";
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //2015-01-06 
        Date date = new Date();
        String sql = "SELECT DISTINCT "
                + "pb.PATIENT_NAME, "
                + "pb.HOME_ADDRESS, "
                + "pb.NEW_IC_NO, "
                + "pb.ID_NO, "
                + "pb.MOBILE_PHONE, "
                + "ch.bill_no, "
                + "ch.txn_date, "
                + "cd.item_cd, "
                + "cd.item_desc, "
                + "cd.quantity, "
                + "cd.item_amt, "
                + "cd.quantity * cd.item_amt AS total "
                + "FROM customer_hdr ch "
                + "INNER JOIN customer_dtl cd "
                + "ON ch.bill_no = cd.bill_no "
                + "INNER JOIN pms_patient_biodata pb "
                + "ON ch.customer_id = pb.ID_NO "
                + "WHERE ch.customer_id = 'M031210009'";
        
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
