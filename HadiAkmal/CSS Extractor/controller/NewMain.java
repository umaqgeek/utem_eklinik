/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Bean.CCN;
import Bean.DGS;
import Bean.DTO;
import Bean.FMH;
import Bean.PMH;
import Bean.VTS;
import Process.MainRetrieval;
import java.util.ArrayList;
import main.RMIConnector;
import pack1.Config;

/**
 *
 * @author ASUS
 */
public class NewMain {
    
    RMIConnector rc = new RMIConnector();
    
    public boolean deleteAll() {
        try {

            String sql1 = "TRUNCATE lhr_signs";
            boolean ccn = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql1);
            String sql2 = "TRUNCATE lhr_diagnosis";
            boolean dgs = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql2);
            String sql3 = "TRUNCATE lhr_medication";
            boolean dto = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql3);
            String sql4 = "TRUNCATE lhr_weight_height";
            boolean vts1 = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql4);
            String sql5 = "TRUNCATE lhr_bp";
            boolean vts2 = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql5);      
            String sql6 = "TRUNCATE lhr_blood_glucose";
            boolean vts3 = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql6);  
            String sql7 = "TRUNCATE lhr_spo2";
            boolean vts4 = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql7);   
            String sql8 = "TRUNCATE lhr_temperature";
            boolean vts5 = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql8);   
            String sql9 = "TRUNCATE lhr_ecg";
            boolean vts6 = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql9); 
            String sql10 = "TRUNCATE lhr_family_history";
            boolean fmh = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql10);   
            String sql11 = "TRUNCATE lhr_past_medical_history";
            boolean pmh = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql11);                      
            
 
            if (ccn && dgs && dto && vts1 && vts2 && vts3 && vts4 && vts5 && vts6 && fmh && pmh) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean view() {


        try {
            String sql1 = "SELECT sii.NATIONAL_ID_NO, sii.PERSON_ID_NO, sii.PERSON_STATUS, sii.LOCATION_CODE, ppb.* FROM PMS_PATIENT_BIODATA ppb, special_integration_information sii WHERE ppb.NEW_IC_NO = sii.NATIONAL_ID_NO LIMIT 100,100";
            ArrayList<ArrayList<String>> data1 = rc.getQuerySQL(Config.ipAddressServer, Config.portServer, sql1);
            
            
            for (int i = 0; i < 50; ++i) System.out.println();

            for (int i = 0; i < data1.size(); i++) {
                String PMI_no = data1.get(i).get(4);
                String NATIONAL_ID_NO = data1.get(i).get(0);
                String PERSON_ID_NO = data1.get(i).get(1);
                String PERSON_STATUS = data1.get(i).get(2);
                String LOCATION_CODE = data1.get(i).get(3);
                String Centre_Code = data1.get(i).get(3);
                //String Central_Code = data1.get(i).get(17);

                String sql2 = "SELECT * FROM EHR_CENTRAL WHERE PMI_NO = '" + PMI_no + "' AND STATUS = 1"; // select undischarge patient (1)

                ArrayList<ArrayList<String>> data2 = rc.getQuerySQL(Config.ipAddressServer, Config.portServer, sql2);

        //        System.out.println("Patient " + PMI_no + ": " + data2.size());

                for (int j = 0; j < data2.size(); j++) { //Show records in arrayList taken from table ehr_central row
                    
                    
                    String Central_Code = data2.get(j).get(0);
                    String episode = data2.get(j).get(2);
                    String txndata1 = data2.get(j).get(3);

                    MainRetrieval mr = new MainRetrieval();
                    mr.startProcess(txndata1);

                    // CCN
                    String dataCCN[][] = mr.getData("CCN");
                    int rowsCCN = mr.getRowNums();

                    // DGS
                    String dataDGS[][] = mr.getData("DGS");
                    int rowsDGS = mr.getRowNums();

                    // DTO
                    String dataDTO[][] = mr.getData("DTO");
                    int rowsDTO = mr.getRowNums();
                    
                    // VTS
                    String dataVTS[][] = mr.getData("VTS");
                    int rowsVTS = mr.getRowNums();
                    
                    // FMH
                    String dataFMH[][] = mr.getData("FMH");
                    int rowsFMH = mr.getRowNums();
                    
                    // PMH
                    String dataPMH[][] = mr.getData("PMH");
                    int rowsPMH = mr.getRowNums();
                    
        //            System.out.println("Central Code " + Central_Code); //this line is to display Central_Code column data.
        //            System.out.println("Episode " + episode + "\nCCN:" + rowsCCN + " DGS:" + rowsDGS + " DTO:" + rowsDTO + " VTS:" + rowsVTS);

                    if (rowsCCN > 0) {

                        ArrayList<CCN> ccnBeans = new ArrayList<CCN>();
                        for (int k = 0; k < rowsCCN; k++) {

                            CCN ccnBean = new CCN();
                            ccnBean.setEpisode_date(dataCCN[k][0]);
                            ccnBean.setPMI_no(PMI_no);
                            ccnBean.setEncounterdate(dataCCN[k][15]);
                            ccnBean.setHfc_cd(dataCCN[k][16]);
                            ccnBean.setTxnDate(dataCCN[k][13]);
                            ccnBean.setSymptom_Code(dataCCN[k][1]);                            
                            ccnBean.setSymptom_Name(dataCCN[k][2]);
                            ccnBean.setTerm_Type(dataCCN[k][19]);
                            ccnBean.setSeverity_Desc(dataCCN[k][4]);
                            ccnBean.setComment(dataCCN[k][12]);
                            ccnBean.setStatus(dataCCN[k][14]);
                            ccnBean.setDoctor_ID(dataCCN[k][17]);
                            ccnBean.setDoctor_Name(dataCCN[k][18]);                            

                            String query1 = "insert into lhr_signs (PMI_no, "
                                    + "hfc_cd,"
                                    + "Episode_Date, "
                                    + "encounterdate, "
                                    + "txnDate, "
                                    + "Symptom_Cd, "
                                    + "Symptom_Name,  "
                                    + "Term_Type,  "
                                    + "severity_desc,  "
                                    + "Comment,  "
                                    + "Status,  "  
                                    + "Doctor_ID,  " 
                                    + "Doctor_Name,  "                                      
                                    + "NATIONAL_ID_NO, "
                                    + "PERSON_ID_NO, "
                                    + "PERSON_STATUS, Centre_Code)"
                                    + "values ('" + ccnBean.getPMI_no() + "',"
                                    + "'" + ccnBean.getHfc_cd() + "',"                                    
                                    + "'" + ccnBean.getEpisode_date() + "',"
                                    + "'" + ccnBean.getEncounterdate() + "',"    
                                    + "'" + ccnBean.getTxnDate() + "',"                                    
                                    + "'" + ccnBean.getSymptom_Code() + "',"
                                    + "'" + ccnBean.getSymptom_Name() + "',"
                                    + "'" + ccnBean.getTerm_Type() + "'," 
                                    + "'" + ccnBean.getSeverity_Desc() + "'," 
                                    + "'" + ccnBean.getComment() + "'," 
                                    + "'" + ccnBean.getStatus() + "',"
                                    + "'" + ccnBean.getDoctor_ID() + "',"                                     
                                    + "'" + ccnBean.getDoctor_Name() + "',"                                     
                                    + "'" + NATIONAL_ID_NO + "',"
                                    + "'" + PERSON_ID_NO + "',"
                                    + "'" + PERSON_STATUS + "',"
                                    + "'" + Centre_Code + "')";

                            boolean status = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, query1);
                            System.out.println("query CCN: " + query1);
                            System.out.println("status CCN " + status);

                            ccnBeans.add(ccnBean);
                        }

                    }

                    if (rowsDGS > 0) {

                        ArrayList<DGS> dgsBr = new ArrayList<DGS>();
                        for (int n = 0; n < rowsDGS; n++) {

                            DGS dgsB = new DGS();
                            dgsB.setPMI_no(PMI_no);
                            dgsB.setEpisode_Date(dataDGS[n][0]);
                            dgsB.setDiagnosis_Type(dataDGS[n][1]);
                            dgsB.setDiagnosis_Type_Desc(dataDGS[n][2]);
                            dgsB.setDiagnosis_Status(dataDGS[n][3]);
                            dgsB.setDiagnosis_Date(dataDGS[n][4]);
                            dgsB.setRead_Code(dataDGS[n][5]);
                            dgsB.setRead_Description(dataDGS[n][6]);
                            dgsB.setDiagnosis_Cd(dataDGS[n][7]);
                            dgsB.setDiagnosis_Desc(dataDGS[n][8]);
                            dgsB.setSeverity_Cd(dataDGS[n][9]);
                            dgsB.setSeverity_Desc(dataDGS[n][10]);
                            dgsB.setSite_Cd(dataDGS[n][11]);
                            dgsB.setSite_Desc(dataDGS[n][12]);
                            dgsB.setEpisodicity_Cd(dataDGS[n][13]);
                            dgsB.setEpisodicity_Desc(dataDGS[n][14]);
                            dgsB.setLaterality_Cd(dataDGS[n][15]);
                            dgsB.setLaterality_Desc(dataDGS[n][16]);
                            dgsB.setCausative_Cd(dataDGS[n][17]);
                            dgsB.setCausative_Desc(dataDGS[n][18]);
                            dgsB.setComment(dataDGS[n][19]);
                            dgsB.setTxn_Date(dataDGS[n][20]);
                            dgsB.setStatus(dataDGS[n][21]);
                            dgsB.setEncounter_Date(dataDGS[n][22]);
                            dgsB.setHFC(dataDGS[n][23]);
                            dgsB.setDoctor_ID(dataDGS[n][24]);
                            dgsB.setDoctor_Name(dataDGS[n][25]);
                            dgsB.setTerm_Type(dataDGS[n][26]);
                            dgsB.setICD10_Code(dataDGS[n][27]);
                            dgsB.setICD10_Desc(dataDGS[n][28]);

                            String query2 = "insert into lhr_diagnosis (PMI_no, "
                                    + "hfc_cd, "
                                    + "episode_date, "
                                    + "encounter_date, "                                    
                                    + "diagnosis_cd, "
                                    + "term_type, "  
                                    + "diagnosis_status, "                                    
                                    + "diagnosis_date, "
                                    + "icd10_cd, "
                                    + "icd10_description, "                                    
                                    + "term_cd, "
                                    + "term_description, "
                                    + "severity, "                                    
                                    + "site, "                                    
                                    + "comment, "                                    
                                    + "status, "                                    
                                    + "doctor_id, "  
                                    + "doctor_name, "                                                                        
                                    + "NATIONAL_ID_NO, "
                                    + "PERSON_ID_NO, "
                                    + "PERSON_STATUS, "
                                    + "centre_code )"
                                    + "values ('" + dgsB.getPMI_no() + "',"
                                    + "'" + dgsB.getHFC() + "',"
                                    + "'" + dgsB.getEpisode_Date() + "',"
                                    + "'" + dgsB.getEncounter_Date() + "',"
                                    + "'" + dgsB.getDiagnosis_Cd() + "',"
                                    + "'" + dgsB.getTerm_Type() + "',"
                                    + "'" + dgsB.getDiagnosis_Status() + "',"
                                    + "'" + dgsB.getDiagnosis_Date() + "',"
                                    + "'" + dgsB.getICD10_Code() + "',"
                                    + "'" + dgsB.getICD10_Desc() + "',"
                                    + "'" + dgsB.getTerm_Type() + "'," //temp
                                    + "'" + dgsB.getTerm_Type() + "'," //temp
                                    + "'" + dgsB.getSeverity_Desc() + "',"
                                    + "'" + dgsB.getSite_Desc() + "',"
                                    + "'" + dgsB.getComment() + "',"                                    
                                    + "'" + dgsB.getStatus() + "',"                                                                        
                                    + "'" + dgsB.getDoctor_ID() + "',"
                                    + "'" + dgsB.getDoctor_Name() + "',"
                                    + "'" + NATIONAL_ID_NO + "',"
                                    + "'" + PERSON_ID_NO + "',"
                                    + "'" + PERSON_STATUS + "',"
                                    + "'" + LOCATION_CODE + "')";

                            boolean stat = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, query2);
        //                    System.out.println("stat:" + stat);
        //                    System.out.println("query:" + query2);

                            dgsBr.add(dgsB);
                        }

                    }

                    if (rowsDTO > 0) {

                        ArrayList<DTO> dtoBrs = new ArrayList<DTO>();
                        for (int m = 0; m < rowsDGS; m++) {

                            DTO dtoC = new DTO();
                            dtoC.setPMI_No(PMI_no);
                            dtoC.setHFC(dataDTO[m][0]);
                            dtoC.setEncounter_Date(dataDTO[m][0]);
                            dtoC.setEpisode_Date(dataDTO[m][0]);                            
                            dtoC.setProblem_Code(dataDTO[m][1]); 
                            dtoC.setProblem_Name(dataDTO[m][2]);                             
                            dtoC.setRequested_Drug_Code(dataDTO[m][4]);
                            dtoC.setDrug_Name(dataDTO[m][5]);
                            dtoC.setProduct_Name(dataDTO[m][5]);                            
                            dtoC.setRequested_Drug_Form_Code(dataDTO[m][7]);
                            dtoC.setRequested_Drug_Form_Desc(dataDTO[m][8]);
                            dtoC.setRequested_Drug_Route_Code_066(dataDTO[m][10]);
                            dtoC.setRequested_Drug_Route_Code_Detail_Reference_Code(dataDTO[m][11]);
                            dtoC.setRequested_Drug_Route_Code_Description(dataDTO[m][12]); // = drug_route_desc
                            dtoC.setRequested_Drug_Frequency_Code(dataDTO[m][13]);
                            dtoC.setRequested_Drug_Frequency_Form_Desc(dataDTO[m][14]);
                            dtoC.setRequested_Drug_Frequency_Unit(dataDTO[m][16]);
                            dtoC.setRequested_Dosage(dataDTO[m][17]);
                            if(dataDTO[m][18] != null && !dataDTO[m][18].isEmpty()) {
                                dtoC.setRequested_Drug_Strength(dataDTO[m][18]);
                            }else{
                                dtoC.setRequested_Drug_Strength("0");
                            }
                            dtoC.setRequested_UOM_Code_025(dataDTO[m][19]);
                            dtoC.setRequested_UOM_Code_Detail_Reference_Code(dataDTO[m][20]);       
                            dtoC.setRequested_UOM_Code_Description(dataDTO[m][21]);                                
                            if(dataDTO[m][22] != null && !dataDTO[m][22].isEmpty()) {
                                dtoC.setRequested_Duration(dataDTO[m][22]);
                            }else{
                                dtoC.setRequested_Duration("0");
                            }      
                            dtoC.setRequested_Quantity(dataDTO[m][23]);
                            dtoC.setStart_Date(dataDTO[m][0]);
                            dtoC.setEnd_Date(dataDTO[m][0]);                            
                           // dtoC.setDoctor_ID(dataDTO[m][23]);
                           // dtoC.setDoctor_Name(dataDTO[m][23]);
                            
                           
                            String query3 = "insert into lhr_medication "
                                    + "(hfc_cd, "
                                    + "episode_date, "                                    
                                    + "pmi_no, "   
                                    + "encounter_date, "                                    
                                    + "drug_cd, "
                                    + "problem_cd, "
                                    + "drug_name, " // = drug desc
                                    + "product_name, "
                                    + "drug_form, " // drug form code
                                    + "drug_form_desc, "
                                    + "drug_route_code, "                                    
                                    + "drug_route_desc, " // = Description
                                    + "drug_freq_code, "
                                    + "drug_freq_desc, "
                                    + "drug_freq_unit, "
                                    + "drug_dosage, "
                                    + "drug_strength, "
                                    + "drug_uom, "
                                    + "duration, "
                                    + "quantity, "
                                    + "start_date, "
                                    + "end_date, "
                                    //+ "doctor_id, "
                                    //+ "doctor_name, "    
                                    + "PERSON_ID_NO, "                                    
                                    + "NATIONAL_ID_NO, "
                                    + "PERSON_STATUS, "
                                    + "centre_code )"
                                    + "values ('" +dtoC.getHFC() + "',"
                                    + "'" + dtoC.getEpisode_Date() + "',"  
                                    + "'" + dtoC.getPMI_No() + "',"  
                                    + "'" + dtoC.getEncounter_Date() + "',"                                      
                                    + "'" + dtoC.getRequested_Drug_Code() + "',"
                                    + "'" + dtoC.getProblem_Code() + "',"                                    
                                    + "'" + dtoC.getDrug_Name() + "'," // drug name
                                    + "'" + dtoC.getProduct_Name() + "',"                                    
                                    + "'" + dtoC.getRequested_Drug_Form_Code() + "',"
                                    + "'" + dtoC.getRequested_Drug_Form_Desc() + "',"
                                    + "'" + dtoC.getRequested_Drug_Route_Code_Detail_Reference_Code() + "',"
                                    + "'" + dtoC.getRequested_Drug_Route_Code_Description() + "',"
                                    + "'" + dtoC.getRequested_Drug_Frequency_Code() + "',"
                                    + "'" + dtoC.getRequested_Drug_Frequency_Form_Desc() + "',"
                                    + "'" + dtoC.getRequested_Drug_Frequency_Unit() + "',"
                                    + "'" + dtoC.getRequested_Dosage() + "',"
                                    + "'" + dtoC.getRequested_Drug_Strength() + "',"
                                    + "'" + dtoC.getRequested_UOM_Code_Detail_Reference_Code() + "',"
                                    + "'" + dtoC.getRequested_Duration() + "',"
                                    + "'" + dtoC.getRequested_Quantity() + "',"  
                                    + "'" + dtoC.getStart_Date() + "',"   
                                    + "'" + dtoC.getEnd_Date() + "',"                                       
                                    + "'" + NATIONAL_ID_NO + "',"
                                    + "'" + PERSON_ID_NO + "',"
                                    + "'" + PERSON_STATUS + "',"
                                    + "'" + LOCATION_CODE + "')";

                            boolean stt = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, query3);
        //                    System.out.println("status dto : " + stt);
        //                    System.out.println("sql dto : " + query3);

                            dtoBrs.add(dtoC);
                        }

                    }
                    
                    
                    if (rowsVTS > 0) {

                        ArrayList<VTS> vts_ArrayList = new ArrayList<VTS>();
                        for (int vts_i = 0; vts_i < rowsVTS; vts_i++) {

                            VTS vts_Obj = new VTS();
                            vts_Obj.setHFC_Cd(dataVTS[vts_i][23]);
                            vts_Obj.setPMI_no(PMI_no);
                            vts_Obj.setEpisode_Date(dataVTS[vts_i][0]);
                            vts_Obj.setEncounter_Date(dataVTS[vts_i][22]);
                            vts_Obj.setWeight_Reading(dataVTS[vts_i][8]);
                            vts_Obj.setHeight_Reading(dataVTS[vts_i][9]);
                            vts_Obj.setDoctor_ID(dataVTS[vts_i][24]);
                            vts_Obj.setDoctor_Name(dataVTS[vts_i][25]);
                            //vts_Obj.setSystolic_Sitting(dataVTS[vts_i][2]);
                            //vts_Obj.setDiastolic_Sitting(dataVTS[vts_i][3]);
                            //vts_Obj.setSitting_Pulse(dataVTS[vts_i][33]);
                            //vts_Obj.setSystolic_Supine(dataVTS[vts_i][4]);
                            //vts_Obj.setDiastolic_Supine(dataVTS[vts_i][5]);
                            //vts_Obj.setSupine_Pulse(dataVTS[vts_i][34]);                            
                            //vts_Obj.setSystolic_Standing(dataVTS[vts_i][6]);
                            //vts_Obj.setDiastolic_Standing(dataVTS[vts_i][7]);
                            //vts_Obj.setStanding_Pulse(dataVTS[vts_i][35]);
                            //vts_Obj.setBlood_Glucose_Level(dataVTS[vts_i][32]);
                            //vts_Obj.setTemperature_Reading(dataVTS[vts_i][1]);
                            vts_Obj.setComment(dataVTS[vts_i][16]);
                            vts_Obj.setSPO2_Reading(dataVTS[vts_i][17]);
                            vts_Obj.setECG_Reading(dataVTS[vts_i][16]);
                            vts_Obj.setECG_Comments(dataVTS[vts_i][16]);
                            
                            //sitting
                            if(dataVTS[vts_i][2] != null && !dataVTS[vts_i][2].isEmpty()) {
                                vts_Obj.setSystolic_Sitting(dataVTS[vts_i][2]);
                            }else{
                                vts_Obj.setSystolic_Sitting("0");
                            }
                            if(dataVTS[vts_i][5] != null && !dataVTS[vts_i][5].isEmpty()) {
                                vts_Obj.setDiastolic_Sitting(dataVTS[vts_i][5]);
                            }else{
                                vts_Obj.setDiastolic_Sitting("0");
                            }
                            if(dataVTS[vts_i][33] != null && !dataVTS[vts_i][33].isEmpty()) {
                                vts_Obj.setSitting_Pulse(dataVTS[vts_i][33]);
                            }else{
                                vts_Obj.setSitting_Pulse("0");
                            }
                            
                            //supine
                            if(dataVTS[vts_i][4] != null && !dataVTS[vts_i][4].isEmpty()) {
                                vts_Obj.setSystolic_Supine(dataVTS[vts_i][4]);
                            }else{
                                vts_Obj.setSystolic_Supine("0");
                            }
                            if(dataVTS[vts_i][2] != null && !dataVTS[vts_i][2].isEmpty()) {
                                vts_Obj.setDiastolic_Supine(dataVTS[vts_i][2]);
                            }else{
                                vts_Obj.setDiastolic_Supine("0");
                            }
                            if(dataVTS[vts_i][34] != null && !dataVTS[vts_i][34].isEmpty()) {
                                vts_Obj.setSupine_Pulse(dataVTS[vts_i][34]);
                            }else{
                                vts_Obj.setSupine_Pulse("0");
                            }
                            
                            // standing
                            if(dataVTS[vts_i][6] != null && !dataVTS[vts_i][6].isEmpty()) {
                                vts_Obj.setSystolic_Standing(dataVTS[vts_i][6]);
                            }else{
                                vts_Obj.setSystolic_Standing("0");
                            }
                            if(dataVTS[vts_i][7] != null && !dataVTS[vts_i][7].isEmpty()) {
                                vts_Obj.setDiastolic_Standing(dataVTS[vts_i][7]);
                            }else{
                                vts_Obj.setDiastolic_Standing("0");
                            }
                            if(dataVTS[vts_i][35] != null && !dataVTS[vts_i][35].isEmpty()) {
                                vts_Obj.setStanding_Pulse(dataVTS[vts_i][35]);
                            }else{
                                vts_Obj.setStanding_Pulse("0");
                            }                            

                            String query_vts_lhr_wh = "insert into lhr_weight_height "
                                    + "(PMI_no, "
                                    + "HFC_Cd, "
                                    + "Episode_Date, "
                                    + "Encounter_Date, "
                                    + "Weight_Reading, "
                                    + "Height_Reading, "
                                    + "Doctor_ID, "
                                    + "Doctor_Name )"
                                    + "values ('" + vts_Obj.getPMI_no() + "',"
                                    + "'" + vts_Obj.getHFC_Cd() + "',"
                                    + "'" + vts_Obj.getEpisode_Date() + "',"
                                    + "'" + vts_Obj.getEncounter_Date() + "',"
                                    + "'" + vts_Obj.getWeight_Reading() + "',"
                                    + "'" + vts_Obj.getHeight_Reading() + "',"
                                    + "'" + vts_Obj.getDoctor_ID() + "',"
                                    + "'" + vts_Obj.getDoctor_Name() + "')";

                            boolean status_vts_lhr_wh = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, query_vts_lhr_wh);
        //                    System.out.println("status vts:" + status_vts_lhr_wh);
        //                    System.out.println("sql vts : " + query_vts_lhr_wh);
                            
                            
                            String query_vts_lhr_bp = "insert into lhr_bp "
                                    + "(PMI_no, "
                                    + "HFC_Cd, "
                                    + "Episode_Date, "
                                    + "Encounter_Date, "
                                    + "Systolic_Sitting, "
                                    + "Diastolic_Sitting, "
                                    + "Sitting_Pulse, "                                   
                                    + "Systolic_Standing, "
                                    + "Diastolic_Standing, " 
                                    + "Standing_Pulse, "  
                                    + "Systolic_Supine, "
                                    + "Diastolic_Supine, "
                                    + "Supine_Pulse, "  
                                    + "Doctor_ID, "
                                    + "Doctor_Name )"                                    
                                    + "values ('" + vts_Obj.getPMI_no() + "',"
                                    + "'" + vts_Obj.getHFC_Cd() + "',"
                                    + "'" + vts_Obj.getEpisode_Date() + "',"
                                    + "'" + vts_Obj.getEncounter_Date() + "',"
                                    + "'" + vts_Obj.getSystolic_Sitting() + "',"
                                    + "'" + vts_Obj.getDiastolic_Sitting() + "',"                                    
                                    + "'" + vts_Obj.getSitting_Pulse() + "',"
                                    + "'" + vts_Obj.getSystolic_Standing() + "',"
                                    + "'" + vts_Obj.getDiastolic_Standing() + "',"
                                    + "'" + vts_Obj.getStanding_Pulse() + "',"
                                    + "'" + vts_Obj.getSystolic_Supine() + "',"
                                    + "'" + vts_Obj.getDiastolic_Supine() + "',"    
                                    + "'" + vts_Obj.getSupine_Pulse() + "',"                                    
                                    + "'" + vts_Obj.getDoctor_ID() + "',"
                                    + "'" + vts_Obj.getDoctor_Name() + "')";

                            boolean status_vts_lhr_bp = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, query_vts_lhr_bp);
                            System.out.println("status vts_lhr_bp:" + status_vts_lhr_bp);
                            System.out.println("sql vts_lhr_bp : " + query_vts_lhr_bp);      
                            

                            //check whether Blood_Glucose_Level data is null or empty
                            if(dataVTS[vts_i][32] != null && !dataVTS[vts_i][32].isEmpty()) {
                                vts_Obj.setBlood_Glucose_Level(dataVTS[vts_i][32]);
                            }else{
                                vts_Obj.setBlood_Glucose_Level("0");
                            }                            
                            
                            // insert record from ehr_central into lhr_blood_glucose table
                            String query_vts_lhr_bg = "insert into lhr_blood_glucose "
                                    + "(PMI_no, "
                                    + "HFC_Cd, "
                                    + "Episode_Date, "
                                    + "Encounter_Date, "
                                    + "Blood_Glucose_Level, "  
                                    + "Doctor_ID, "
                                    + "Doctor_Name )"                                    
                                    + "values ('" + vts_Obj.getPMI_no() + "',"
                                    + "'" + vts_Obj.getHFC_Cd() + "',"
                                    + "'" + vts_Obj.getEpisode_Date() + "',"
                                    + "'" + vts_Obj.getEncounter_Date() + "',"  
                                    + "'" + vts_Obj.getBlood_Glucose_Level() + "',"                                    
                                    + "'" + vts_Obj.getDoctor_ID() + "',"
                                    + "'" + vts_Obj.getDoctor_Name() + "')";

                            boolean status_vts_lhr_bg = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, query_vts_lhr_bg);
                            System.out.println("status vts_lhr_bg:" + status_vts_lhr_bg);
                            System.out.println("sql vts_lhr_bg : " + query_vts_lhr_bg);
                            
                            
                            //check whether spo2 data is null or empty
                            if(dataVTS[vts_i][17] != null && !dataVTS[vts_i][17].isEmpty()) {
                                vts_Obj.setSPO2_Reading(dataVTS[vts_i][17]);
                            }else{
                                vts_Obj.setSPO2_Reading("0");
                            }
                            
                            // insert into lhr_spo2 table for VTS
                            String query_vts_lhr_spo2 = "insert into lhr_spo2 "
                                    + "(PMI_no, "
                                    + "HFC_Cd, "
                                    + "Episode_Date, "
                                    + "Encounter_Date, "
                                    + "SPO2_Reading, "  
                                    + "Doctor_ID, "
                                    + "Doctor_Name )"                                    
                                    + "values ('" + vts_Obj.getPMI_no() + "',"
                                    + "'" + vts_Obj.getHFC_Cd() + "',"
                                    + "'" + vts_Obj.getEpisode_Date() + "',"
                                    + "'" + vts_Obj.getEncounter_Date() + "',"  
                                    + "'" + vts_Obj.getSPO2_Reading() + "',"                                    
                                    + "'" + vts_Obj.getDoctor_ID() + "',"
                                    + "'" + vts_Obj.getDoctor_Name() + "')";

                            boolean status_vts_lhr_spo2 = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, query_vts_lhr_spo2);
                            System.out.println("status vts_lhr_spo2:" + status_vts_lhr_spo2);
                            System.out.println("sql vts_lhr_spo2 : " + query_vts_lhr_spo2);     
                            
                            
                            // insert into lhr_procedure table for VTS
                            String query_vts_lhr_procedure = "insert into lhr_procedure "
                                    + "(PMI_no, "
                                    + "HFC_Cd, "
                                    + "Episode_Date, "
                                    + "Encounter_Date, "
                                    + "Procedure_Cd, "
                                    + "Procedure_Name, "  
                                    + "Procedure_Outcome, "  
                                    + "Comment, "                                      
                                    + "Doctor_ID, "
                                    + "Doctor_Name, "                                      
                                    + "Centre_Code )"                                    
                                    + "values ('" + vts_Obj.getPMI_no() + "',"
                                    + "'" + vts_Obj.getHFC_Cd() + "',"
                                    + "'" + vts_Obj.getEpisode_Date() + "',"
                                    + "'" + vts_Obj.getEncounter_Date() + "',"  
                                    + "'" + vts_Obj.getProcedure_Cd() + "',"   
                                    + "'" + vts_Obj.getProcedure_Name() + "'," 
                                    + "'" + vts_Obj.getProcedure_Outcome() + "'," 
                                    + "'" + vts_Obj.getComment() + "'," 
                                    + "'" + vts_Obj.getDoctor_ID() + "',"                                     
                                    + "'" + vts_Obj.getDoctor_Name() + "',"
                                    + "'" + LOCATION_CODE + "')";

                            boolean status_vts_lhr_procedure = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, query_vts_lhr_procedure);
                            System.out.println("status vts_lhr_procedure:" + status_vts_lhr_procedure);
                            System.out.println("sql vts_lhr_procedure : " + query_vts_lhr_procedure);         
                            
                            
                            //check whether temperature_reading data is null or empty
                            if(dataVTS[vts_i][1] != null && !dataVTS[vts_i][1].isEmpty()) {
                                vts_Obj.setTemperature_Reading(dataVTS[vts_i][1]);
                            }else{
                                vts_Obj.setTemperature_Reading("0");
                            }
                            
                            // insert into lhr_procedure table for VTS
                            String query_vts_lhr_temperature = "insert into lhr_temperature "
                                    + "(PMI_no, "
                                    + "HFC_Cd, "
                                    + "Episode_Date, "
                                    + "Encounter_Date, "  
                                    + "temperature_reading, "  
                                    + "comment, "                                      
                                    + "doctor_id, "
                                    + "doctor_name, "                                      
                                    + "centre_code )"                                    
                                    + "values ('" + vts_Obj.getPMI_no() + "',"
                                    + "'" + vts_Obj.getHFC_Cd() + "',"
                                    + "'" + vts_Obj.getEpisode_Date() + "',"
                                    + "'" + vts_Obj.getEncounter_Date() + "',"  
                                    + "'" + vts_Obj.getTemperature_Reading() + "'," 
                                    + "'" + vts_Obj.getComment() + "'," 
                                    + "'" + vts_Obj.getDoctor_ID() + "',"                                     
                                    + "'" + vts_Obj.getDoctor_Name() + "',"
                                    + "'" + LOCATION_CODE + "')";

                            boolean status_vts_lhr_temperature = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, query_vts_lhr_temperature);
                            System.out.println("status status_vts_lhr_temperature :" + status_vts_lhr_temperature);
                            System.out.println("sql vts_lhr_temperature : " + query_vts_lhr_temperature);                                 
                         

                            vts_ArrayList.add(vts_Obj);
                        }

                    }                    

                            
                    if (rowsFMH > 0) {

                        ArrayList<FMH> fmh_ArrayList = new ArrayList<FMH>();
                        for (int fmh_i = 0; fmh_i < rowsFMH; fmh_i++) {

                            FMH fmh_Obj = new FMH();
                            fmh_Obj.setPMI_No(PMI_no);
                            fmh_Obj.setEpisode_Date(dataFMH[fmh_i][0]);
                            fmh_Obj.setFamily_Relationship_Cd(dataFMH[fmh_i][1]);
                            fmh_Obj.setICD10_Code(dataDGS[fmh_i][2]);
                            fmh_Obj.setICD10_Description(dataFMH[fmh_i][3]);
                            fmh_Obj.setRead_Code(dataDGS[fmh_i][4]);
                            fmh_Obj.setRead_Description(dataFMH[fmh_i][5]);
                            fmh_Obj.setDate_Onset(dataFMH[fmh_i][6]);
                            fmh_Obj.setComments(dataFMH[fmh_i][7]);
                            fmh_Obj.setAnswer_Code(dataFMH[fmh_i][8]);
                            fmh_Obj.setAnswer_Desc(dataFMH[fmh_i][9]);
                            fmh_Obj.setTxn_Date(dataFMH[fmh_i][10]);
                            fmh_Obj.setStatus(dataFMH[fmh_i][11]);
                            fmh_Obj.setEncounter_Date(dataFMH[fmh_i][12]);
                            fmh_Obj.setHFC(dataFMH[fmh_i][13]);
                            fmh_Obj.setDoctor_Id(dataFMH[fmh_i][14]);
                            fmh_Obj.setDoctor_Name(dataFMH[fmh_i][15]);
                            fmh_Obj.setTerm_Type(dataFMH[fmh_i][16]);
                            fmh_Obj.setTerm_Code("ICD10_CODE");


                            String query_fmh_lhr_fh = "insert into lhr_family_history (PMI_no, "
                                    + "hfc_cd, "
                                    + "episode_date, "
                                    + "encounter_date, "                                    
                                    + "diagnosis_cd, " //insert icd10 code
                                    + "onset_date, " //insert icd10 code                                    
                                    + "term_type, "  
                                  //  + "diagnosis_status, "                                    
                                  //  + "diagnosis_date, "
                                    + "icd10_cd, "
                                    + "icd10_description, "                                    
                                    + "term_cd, "
                                  //  + "term_description, "                               
                                    + "comment, "                                    
                                    + "status, "                                    
                                    + "doctor_id, "  
                                    + "doctor_name, "                                                                        
                                    + "NATIONAL_ID_NO, "
                                    + "PERSON_ID_NO, "
                                    + "PERSON_STATUS, "
                                    + "centre_code )"
                                    + "values ('" + fmh_Obj.getPMI_No() + "',"
                                    + "'" + fmh_Obj.getHFC() + "',"
                                    + "'" + fmh_Obj.getEpisode_Date() + "',"
                                    + "'" + fmh_Obj.getEncounter_Date() + "',"
                                    + "'" + fmh_Obj.getICD10_Code() + "',"// diagnosis_cd
                                    + "'" + fmh_Obj.getDate_Onset() + "',"                                   
                                    + "'" + fmh_Obj.getTerm_Type()+ "',"
                                    + "'" + fmh_Obj.getICD10_Code()+ "',"
                                    + "'" + fmh_Obj.getICD10_Description()+ "',"
                                    + "'" + fmh_Obj.getTerm_Code()+ "'," 
                                    + "'" + fmh_Obj.getComments() + "',"
                                    + "'" + fmh_Obj.getStatus() + "',"
                                    + "'" + fmh_Obj.getDoctor_Id() + "',"
                                    + "'" + fmh_Obj.getDoctor_Name() + "'," //temp
                                    + "'" + NATIONAL_ID_NO + "',"
                                    + "'" + PERSON_ID_NO + "',"
                                    + "'" + PERSON_STATUS + "',"
                                    + "'" + LOCATION_CODE + "')";

                            boolean status_fmh_lhr_fh = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, query_fmh_lhr_fh);
                            System.out.println("status : " + status_fmh_lhr_fh);
                            System.out.println("query : " + query_fmh_lhr_fh);

                            fmh_ArrayList.add(fmh_Obj);
                        }

                    } //FMH end
                    
                    
                    if (rowsPMH > 0) {

                        ArrayList<PMH> pmh_ArrayList = new ArrayList<PMH>();
                        for (int pmh_i = 0; pmh_i < rowsPMH; pmh_i++) {

                            PMH pmh_Obj = new PMH();
                            pmh_Obj.setPMI_No(PMI_no);
                            pmh_Obj.setEpisode_Date(dataPMH[pmh_i][0]);
                            pmh_Obj.setRead_Code(dataPMH[pmh_i][1]);
                            pmh_Obj.setRead_Description(dataPMH[pmh_i][2]);
                            pmh_Obj.setDiagnosis_Status(dataPMH[pmh_i][3]);
                            pmh_Obj.setDiagnosis_Status_Des(dataPMH[pmh_i][4]);
                            pmh_Obj.setDiagnosis_Date(dataPMH[pmh_i][5]);
                            pmh_Obj.setComments(dataPMH[pmh_i][6]);
                            pmh_Obj.setAnswer_Code(dataPMH[pmh_i][7]);
                            pmh_Obj.setAnswer_Desc(dataPMH[pmh_i][8]);
                            pmh_Obj.setTxn_Date(dataPMH[pmh_i][9]);
                            pmh_Obj.setStatus(dataPMH[pmh_i][10]);
                            pmh_Obj.setEncounter_Date(dataPMH[pmh_i][11]);
                            pmh_Obj.setHFC(dataPMH[pmh_i][12]);
                            pmh_Obj.setDoctor_Id(dataPMH[pmh_i][13]);
                            pmh_Obj.setDoctor_Name(dataPMH[pmh_i][14]);
                            pmh_Obj.setTerm_Type(dataPMH[pmh_i][15]);
                            pmh_Obj.setICD10_Code(dataPMH[pmh_i][16]);                            
                            pmh_Obj.setICD10_Desc(dataPMH[pmh_i][17]);
                            
                            String query_pmh_lhr_pmh = "insert into lhr_past_medical_history (PMI_no, "
                                    + "hfc_cd, "
                                   // + "onset_date, "                                    
                                    + "encounter_date, "                                    
                                    + "diagnosis_cd, " //insert icd10_code
                                  //  + "diagnosis_date, "                                    
                                    + "term_type, "                                    
                                    + "icd10_cd, "
                                    + "icd10_description, "                                    
                                  //  + "term_cd, "
                                  //  + "term_description, "                               
                                    + "comment, "                                    
                                    + "status, "                                    
                                    + "doctor_id, "  
                                    + "doctor_name, "                                                                        
                                    + "NATIONAL_ID_NO, "
                                    + "PERSON_ID_NO, "
                                    + "PERSON_STATUS, "
                                    + "centre_code )"
                                    + "values ('" + pmh_Obj.getPMI_No() + "',"
                                    + "'" + pmh_Obj.getHFC() + "',"
                                    + "'" + pmh_Obj.getEncounter_Date() + "',"
                                    + "'" + pmh_Obj.getICD10_Code() + "',"  //diagnosis_cd                                  
                                    + "'" + pmh_Obj.getTerm_Type() + "',"
                                    + "'" + pmh_Obj.getICD10_Code() + "',"
                                    + "'" + pmh_Obj.getICD10_Desc() + "',"
                                    + "'" + pmh_Obj.getComments() + "',"
                                    + "'" + pmh_Obj.getStatus() + "',"
                                    + "'" + pmh_Obj.getDoctor_Id() + "',"
                                    + "'" + pmh_Obj.getDoctor_Name() + "',"
                                    + "'" + NATIONAL_ID_NO + "',"
                                    + "'" + PERSON_ID_NO + "',"
                                    + "'" + PERSON_STATUS + "',"
                                    + "'" + LOCATION_CODE + "')";

                            boolean status_pmh_lhr_pmh = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, query_pmh_lhr_pmh);
                            System.out.println("status : " + status_pmh_lhr_pmh);
                            System.out.println("query : " + query_pmh_lhr_pmh);

                            pmh_ArrayList.add(pmh_Obj);
                        }

                    } //FMH end
                    
                    String sql_update_ehr_central = "UPDATE `ehr_central` SET `STATUS` = '3' WHERE `ehr_central`.`CENTRAL_CODE` = '" + Central_Code + "'"; // Update patient status to 3 by using CENTRAL_CODE unique column data.
                    boolean update_ehr_central_boolean = rc.setQuerySQL(Config.ipAddressServer, Config.portServer, sql_update_ehr_central);                      
            
                    if (update_ehr_central_boolean) { //get update status
                        System.out.println("Patient with Central Code : " + Central_Code + " has updated to 3");
                    } else {
                        System.out.println("Patient with Central Code : " + Central_Code + " has failed update to 3");
                    }                    

                    
                } // for loop for EHR_Central records end 

            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
