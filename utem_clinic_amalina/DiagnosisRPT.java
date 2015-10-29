/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package eklinik;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.List;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;

/**
 *
 * @author Hadi Akmal
 */
public class DiagnosisRPT extends javax.swing.JFrame {

    String faculty = null; //public var
    String unikID = null;

    /**
     * Creates new form NewJFrame
     */
    public DiagnosisRPT() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"FTMK", "FKP", "FKM", "FKEKK", "FKE", "FPTT", "FTK"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Select a faculty : ");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Generate Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(145, 145, 145)
                                        .addComponent(jButton1)))
                        .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>                        

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        Document document = new Document();

        jTextArea1.setText(""); //clear textarea
        //----wrap lines AND wrap at word boundaries and not character boundaries
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        //--------------------------->
        Object selectedItem = jComboBox1.getSelectedItem();
        faculty = selectedItem.toString();
        if (selectedItem != null) {
            try {

                unikID = UUID.randomUUID().toString().replaceAll("[\\s-]+", "3"); //generate unique ID : http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html Replace white space and dash with number 3
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(faculty + "-" + unikID + ".pdf"));

                document.open();

		//buat column plg banyak dulu untuk mudahkan design
                PdfPTable table = new PdfPTable(6);
                table.getDefaultCell().setBorder(0);
                table.setTotalWidth(520);
                table.setWidths(new float[]{1.5f, 3, 3.5f, 3, 30, 3}); //guna float untuk precisekan column width
                PdfPCell cell;

                //initialize mysql con and var data type
                Integer tot_by_fac = null;
                ResultSet rs;
                ResultSet rs_block;
                ResultSet rs_code;
                String query = null;
                String remove_last_char;
                String connectionURL;
                String chapter_total_result = null;

                //initialize pdf
                Font teks = new Font(Font.HELVETICA, 18, Font.BOLD);
                Color orange = WebColors.getRGBColor("Orange");
                Color magenta = WebColors.getRGBColor("#FF00FF");
                Color cyan = WebColors.getRGBColor("#00FFFF");

                connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=1234";
                Connection conn = DriverManager.getConnection(connectionURL);
                PreparedStatement st1 = conn.prepareStatement(connectionURL);

                try {

                    HashMap<String, String> chapter_map = new HashMap<>();
                    ArrayList<String> chapter_list = new ArrayList<String>();
                    ArrayList<String> invalid_record_list = new ArrayList<String>();
                    query = "SELECT * FROM `icd10_chapters` ORDER  BY `icd10_chapters`.`Id` ASC";
                    rs = st1.executeQuery(query);

                    while (rs.next()) {
                        chapter_map.put(rs.getString("id"), rs.getString("name"));
                        chapter_list.add(rs.getString("id")); //assign mysql result to list
                        chapter_list.add(rs.getString("name")); //assign mysql result to list
                        
                    }

                    query = "SELECT COUNT(*) AS tot_by_fac FROM `lhr_diagnosis` WHERE `lhr_diagnosis`.`LOCATION_CODE` = ?";
                    st1 = conn.prepareStatement(query);
                    st1.setString(1, faculty);
                    rs = st1.executeQuery();

                    while (rs.next()) {
                        tot_by_fac = rs.getInt("tot_by_fac");
                    }

                    //jTextArea1.append("Total patient by faculty : " + tot_by_fac);
                    //jTextArea1.append("\nTotal patient by chapter : \n\n");

                    PdfPTable table2 = new PdfPTable(2);
                    float[] columnWidths = {2f, 1.19f};
                    table2.setWidths(columnWidths);
                    // 2 columns.
                    Image logo = Image.getInstance("logoUTeMPNG.png");
                    logo.scaleAbsolute(230, 100);
                        //logo.scalePercent(5f);

                    PdfPCell cell1 = new PdfPCell(logo);
                    cell1.setBorder(Rectangle.NO_BORDER);
                    cell1.setLeading(15f, 0.3f);

                    PdfPCell cell2 = new PdfPCell(new Paragraph("Universiti Teknikal Malaysia Melaka\nHang Tuah Jaya, \n76100 Durian Tunggal, \nMelaka, Malaysia."));
                    cell2.setBorder(Rectangle.NO_BORDER);
                    //cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell2.setLeading(15f, 0.3f);

                    table2.addCell(cell1);
                    table2.addCell(cell2);

                    PdfPCell cell3 = new PdfPCell(new Paragraph("\nDiagnosis Report by Faculty", teks));
                    cell3.setBorder(Rectangle.NO_BORDER);
                    PdfPCell cell4 = new PdfPCell(new Paragraph("\n\n\n"));
                    cell4.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell3);
                    table2.addCell(cell4);

                    PdfPCell cell5 = new PdfPCell(new Paragraph("Faculty : " + faculty));
                    cell5.setBorder(Rectangle.NO_BORDER);

                    String timeStamp = new SimpleDateFormat("dd-MM-yyyy h:mm a").format(Calendar.getInstance().getTime());
                    PdfPCell cell6 = new PdfPCell(new Paragraph("Date : " + timeStamp));
                    cell6.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell5);
                    table2.addCell(cell6);

                    PdfPCell cell7 = new PdfPCell(new Paragraph(""));
                    //PdfPCell cell7 = new PdfPCell(new Paragraph("Total Diagnosis: " + tot_by_fac));
                    cell7.setBorder(Rectangle.NO_BORDER);

                    PdfPCell cell8 = new PdfPCell(new Paragraph("Report ID : ECSS_RPT_001")); //remove with space and dash
                    cell8.setBorder(Rectangle.NO_BORDER);
                    table2.addCell(cell7);
                    table2.addCell(cell8);

                    document.add(table2);

                        //temp validation         
                    //Check for invalid data in DiagnosisCd column
                    query = "select DiagnosisCd from lhr_diagnosis LEFT JOIN icd10_codes ON lhr_diagnosis.DiagnosisCd = icd10_codes.icd10_code WHERE icd10_code IS NULL AND LOCATION_CODE = ?";

                    st1 = conn.prepareStatement(query); //recreate statement
                    st1.setString(1, faculty); // set input parameter
                    rs = st1.executeQuery();

                    while (rs.next()) {
                        invalid_record_list.add(rs.getString("DiagnosisCd")); //assign mysql result to list
                    }

                    //document.add(new Phrase(""));
                    Paragraph paragraph1 = new Paragraph();
                    paragraph1.add("");
                    paragraph1.setSpacingAfter(12);
                    document.add(paragraph1);

                    int i = 0;
                    int n = 0;

                    Map<String, PdfPTable> testObjs = new HashMap<String, PdfPTable>();

                    for (i = 1; i <= chapter_map.size(); i++) { // chapter_map.size() is total of keys during .put

                        //jTextArea1.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

                        // only 1 chapter is needed                 
                        query = "select COUNT(DiagnosisCd) AS count from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND substring(DiagnosisCd,1,2) =  '" + String.format("%02d", i) + "' AND LOCATION_CODE = ?";

                        st1 = conn.prepareStatement(query); //recreate statement
                        st1.setString(1, faculty); // set input parameter
                        rs = st1.executeQuery();

                        while (rs.next()) {
                            chapter_total_result = rs.getString("count");
                        }

                        //jTextArea1.append(String.format("%02d", i) + "   " + chapter_map.get(String.format("%02d", i)) + "   " + chapter_total_result);

                        //System.out.format("Current cursor " +i + ": %f%n", writer.getVerticalPosition(true));
                        if (writer.getVerticalPosition(true) <= 90.000000 || writer.getVerticalPosition(true) <= 112.000000) { //if chapter title is at the bottom then push it to new page. 112.000000 is title cursor for first page & 90.000000 is chapter title cursor other than 1st page.
                            //document.newPage();
                        }

                        //chapter row
                        testObjs.put("chapter", new PdfPTable(6)); //declare new object. this object will be overwrite by new similar object name during the next loop
                        testObjs.get("chapter").getDefaultCell().setBorder(0);
                        testObjs.get("chapter").setWidths(new float[]{2, 3, 3.5f, 3, 30, 5.9f}); //guna float untuk precisekan column width
                        testObjs.get("chapter").setLockedWidth(true);
                        testObjs.get("chapter").setTotalWidth(document.right() - document.left());

                        //chapter row
                        cell = new PdfPCell(new Phrase(String.format("%02d", i)));
                        cell.setColspan(1);
                        
                                cell.setBorder(Rectangle.NO_BORDER);
                        //cell.setBackgroundColor(orange);
                        testObjs.get("chapter").addCell(cell);
                        cell = new PdfPCell(new Phrase(chapter_map.get(String.format("%02d", i))));
                        //cell.setBackgroundColor(orange);
                        cell.setColspan(4);
                        
                                cell.setBorder(Rectangle.NO_BORDER);
                        testObjs.get("chapter").addCell(cell);
                        cell = new PdfPCell(new Phrase(chapter_total_result));
                        //cell.setBackgroundColor(orange);
                        cell.setColspan(1);
                        
                                cell.setBorder(Rectangle.NO_BORDER);
                        testObjs.get("chapter").addCell(cell);

                        document.add(testObjs.get("chapter"));

                        if (!"0".equals(chapter_total_result)) {	// check chapter_total_result. if != 0 enter loop

                            //jTextArea1.append("\n\n\tTotal Patient by Block :");

                            //block row     
                            testObjs.put("block_title", new PdfPTable(6));
                            testObjs.get("block_title").getDefaultCell().setBorder(0);
                            testObjs.get("block_title").setWidths(new float[]{2, 3, 3.5f, 3, 30, 5.9f});
                            testObjs.get("block_title").setLockedWidth(true);
                            testObjs.get("block_title").setTotalWidth(document.right() - document.left());

                            //block row
                            cell = new PdfPCell(new Phrase(""));
                            //cell.setColspan(5);
                            cell.setBorder(Rectangle.NO_BORDER);
                            testObjs.get("block_title").addCell(cell);
                            cell = new PdfPCell(new Phrase("Total Patient by Block :"));
                            cell.setColspan(6);
                            
                                cell.setBorder(Rectangle.NO_BORDER);
                            //cell.setBackgroundColor(magenta);
                            testObjs.get("block_title").addCell(cell);
                            document.add(testObjs.get("block_title"));

                            query = "SELECT DiagnosisCd, idc, id, name, total FROM icd10_blocks, (SELECT DiagnosisCd, substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE id = diag AND idc = '" + String.format("%02d", i) + "'";

                            st1 = conn.prepareStatement(query); //recreate statement
                            st1.setString(1, faculty); // set input parameter
                            rs_block = st1.executeQuery();

                            while (rs_block.next()) {

                                //System.out.println(block_counter++);
                                String block_id_result = rs_block.getString("id");
                                String block_name_result = rs_block.getString("name");
                                String block_total_result = rs_block.getString("total");
                                //jTextArea1.append("\n\t" + block_id_result + "   " + block_name_result + "   "+ block_total_result +"\n");
                                //jTextArea1.append("\n\t" + block_id_result + "   " + block_name_result + "   " + block_total_result + "\n");

                                testObjs.put("block", new PdfPTable(6));

                                testObjs.get("block").getDefaultCell().setBorder(0);
                                testObjs.get("block").setWidths(new float[]{2, 3, 3.5f, 3, 30, 5.9f});
                                testObjs.get("block").setLockedWidth(true);
                                testObjs.get("block").setTotalWidth(document.right() - document.left());
                                //System.out.println("loop block nombor :" + i);
                                cell = new PdfPCell(new Phrase(""));
                                cell.setColspan(1);
                                cell.setBorder(Rectangle.NO_BORDER);
                                testObjs.get("block").addCell(cell);
                                
                                cell = new PdfPCell(new Phrase(block_id_result));
                                cell.setColspan(1);
                                cell.setBorder(Rectangle.NO_BORDER);
                                //cell.setBackgroundColor(magenta);
                                testObjs.get("block").addCell(cell);
                                cell = new PdfPCell(new Phrase(block_name_result));
                                cell.setColspan(3);
                                
                                cell.setBorder(Rectangle.NO_BORDER);
                                //cell.setBackgroundColor(magenta);
                                testObjs.get("block").addCell(cell);
                                cell = new PdfPCell(new Phrase(block_total_result));
                                cell.setColspan(1);
                                
                                cell.setBorder(Rectangle.NO_BORDER);
                                //cell.setBackgroundColor(magenta);
                                testObjs.get("block").addCell(cell);
                                document.add(testObjs.get("block"));

                                remove_last_char = block_id_result.substring(0, block_id_result.length() - 1); //remove last character in 'id' resultset retrieve from icd10_blocks table. A00 = A0

                                //jTextArea1.append("\n\t\tTotal Patient by Code :");

                                if (n != i) { //To make sure code title will be display only one time for every chapter. Check whether n == previous record of i. If not similar then display Code Title

                                    if (writer.getPageNumber() == 1 && writer.getVerticalPosition(true) <= 78.000000) { //This to check block records above for page 1
                                        //System.out.format("Current cursor " +i + ": %f%n", writer.getVerticalPosition(true));  
                                        document.newPage();
                                    }

                                    if (writer.getPageNumber() != 1 && writer.getVerticalPosition(true) <= 74.000000) { ////This to check block records above other than page 1
                                        document.newPage();
                                    }

                                    // code row
                                    testObjs.put("code_title", new PdfPTable(6));
                                    testObjs.get("code_title").getDefaultCell().setBorder(0);
                                    testObjs.get("code_title").setWidths(new float[]{2, 3, 3.5f, 3, 30, 5.9f});
                                    testObjs.get("code_title").setLockedWidth(true);
                                    testObjs.get("code_title").setTotalWidth(document.right() - document.left());

                                    cell = new PdfPCell(new Phrase(""));
                                    cell.setColspan(2);
                                    cell.setBorder(Rectangle.NO_BORDER);
                                    testObjs.get("code_title").addCell(cell);
                                    cell = new PdfPCell(new Phrase("Total Patient by Code :"));
                                    cell.setColspan(4);
                                    
                                cell.setBorder(Rectangle.NO_BORDER);
                                    //cell.setBackgroundColor(cyan);
                                    testObjs.get("code_title").addCell(cell);
                                    document.add(testObjs.get("code_title"));
                                    n = i;

                                }

                                query = "SELECT ld.diagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, ib.Id AS icd10_block, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_blocks ib, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,3,3) ='" + block_id_result + "' AND ib.Id = '" + block_id_result + "' AND ic.icd10_code = ld.DiagnosisCd AND LOCATION_CODE = ? group by DiagnosisCd";
                                      //query = "SELECT ld.DiagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,3,3) ='"+ block_id_result +"' AND ld.DiagnosisCd = ic.icd10_code AND LOCATION_CODE = ? group by DiagnosisCd;";
                                //String query_01_code = "SELECT substring(DiagnosisCd,6,5) as diag, COUNT(DiagnosisCd) as total from lhr_diagnosis WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,6,2) ='"+ remove_last_char +"'  group by DiagnosisCd";

                                st1 = conn.prepareStatement(query); //recreate statement
                                st1.setString(1, faculty); // set input parameter
                                rs_code = st1.executeQuery();
                                      //rs_code = st2.executeQuery(query);

                                while (rs_code.next()) {

                                    String code_strip_result = rs_code.getString("icd10_code_strip");
                                    String code_desc_result = rs_code.getString("icd10_desc");
                                    String code_total_result = rs_code.getString("total");
                                    
                                    //------------------------test
                                    String format = "%1$5s %2$-20s %3$-20s";
                                    String someLine;
                                    someLine = String.format(format, code_strip_result, code_desc_result, code_total_result);
                                    //jTextArea1.append("\n\t\t" + code_strip_result + "\t" + code_desc_result + "\t" + code_total_result);
                                   
                                    //-----------------------------
                                    
                                    //jTextArea1.append("\n\t\t" + code_strip_result + "\t" + code_desc_result + "\t" + code_total_result);
                                          //jTextArea1.append("\n\t\t" + code_strip_result + "\t" + code_desc_result + "\t"+ code_total_result);

                                    testObjs.put("code", new PdfPTable(6));
                                    //System.out.println("loop nombor :" + i);
                                    testObjs.get("code").getDefaultCell().setBorder(0);
                                    testObjs.get("code").setWidths(new float[]{2, 3, 3.5f, 3, 30, 5.9f});
                                    testObjs.get("code").setLockedWidth(true);
                                    testObjs.get("code").setTotalWidth(document.right() - document.left());

                                    cell = new PdfPCell(new Phrase(""));
                                    cell.setColspan(2);
                                    cell.setBorder(Rectangle.NO_BORDER);
                                    testObjs.get("code").addCell(cell);
                                    cell = new PdfPCell(new Phrase(code_strip_result));
                                    cell.setColspan(1);
                                    
                                cell.setBorder(Rectangle.NO_BORDER);
                                    //cell.setBackgroundColor(cyan);
                                    testObjs.get("code").addCell(cell);
                                    cell = new PdfPCell(new Phrase(code_desc_result));
                                    cell.setColspan(2);
                                    
                                cell.setBorder(Rectangle.NO_BORDER);
                                    //cell.setBackgroundColor(cyan);
                                    testObjs.get("code").addCell(cell);
                                    cell = new PdfPCell(new Phrase(code_total_result));
                                    cell.setColspan(1);
                                    
                                cell.setBorder(Rectangle.NO_BORDER);
                                    //cell.setBackgroundColor(cyan);
                                    testObjs.get("code").addCell(cell);
                                    document.add(testObjs.get("code"));

                                }// code loop end

                                if (writer.getVerticalPosition(true) <= 88.000000) { //Block title at first page use 88.000000
                                    //document.newPage();
                                }
                                      //System.out.format("Current cursor " +i + ": %f%n", writer.getVerticalPosition(true));

                            }// block loop end
                        }// if bracket end

                        //jTextArea1.append("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                        //jTextArea1.append("\n\n");
                    } // for loop end       

                    if (invalid_record_list.size() != 0) {
                        document.add(new Phrase("\n"));
                        document.add(new Phrase(invalid_record_list.size() + " invalid records founded in table `lhr_diagnosis`.`DiagnosisCd` : " + invalid_record_list));
                    }
                    /*       
                     Iterator<String> iterator = testObjs.keySet().iterator();

                     while (iterator.hasNext()) {
                     String key = iterator.next().toString();
                     PdfPTable value = testObjs.get(key);

                     System.out.println(key + " " + value);
                     }
                        
                     System.out.println(chapter_list);
                     System.out.println(chapter_list.get(1));
                     System.out.println(invalid_record_list);
                     System.out.println(invalid_record_list.size());
                        
                     Set<String> keys = testObjs.keySet();

                     // Loop over String keys.
                     for (String key : keys) {
                     System.out.println(key);
                     }
                     Collection<PdfPTable> values = testObjs.values();
                     for (PdfPTable value : values) {
                     System.out.println(value);
                     }
                     */

                } catch (Exception e) {
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }

                    //table.setWidthPercentage(100);
                //document.add(table);
                document.close();

                conn.close();

            }// if for select box end
            // jComboBox1ActionPerformed end bracket   
            catch (SQLException ex) {
                Logger.getLogger(DiagnosisRPT.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {// catch itextpdf exception
                e.printStackTrace();
            }

        }

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        // open pdf file platform independent
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(faculty + "-" + unikID + ".pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        int i = 0;
        int x = 0;

        for (i = 01; i <= 22; i++) {
            String.format("%02d", i);
              //System.out.println(String.format("%02d", i)); // 0 to pad with zeros. 2 to set width to two

            int chapter = x;
                  //System.out.print(chapter);
            //System.out.print(" ");
            int description = x + 1;
            //System.out.print(description);
            x = x + 2;
                  //x++;
            //System.out.print("\n");

        }

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DiagnosisRPT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiagnosisRPT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiagnosisRPT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiagnosisRPT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DiagnosisRPT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration                   
}
