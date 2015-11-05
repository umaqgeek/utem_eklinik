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
import com.itextpdf.text.Element;
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
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.ExceptionConverter;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.Desktop;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.plaf.basic.CalendarHeaderHandler;
import org.jdesktop.swingx.plaf.basic.SpinningCalendarHeaderHandler;

/**
 *
 * @author Hadi Akmal
 */
public class NewJFrame extends javax.swing.JFrame {
    
    String faculty = null; //public var


    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
    }
    
        class TableHeader extends PdfPageEventHelper {
        /** The header text. */
        String header;
        /** The template with the total number of pages. */
        PdfTemplate total;
 
        /**
         * Allows us to change the content of the header.
         * @param header The new header String
         */
        public void setHeader(String header) {
            this.header = header;
        }
 
        /**
         * Creates the PdfTemplate that will hold the total number of pages.
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
         *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }
 
        /**
         * Adds a header to every page
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
         *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable table = new PdfPTable(3);
            try {
                table.setWidths(new int[]{24, 24, 2});
                table.setTotalWidth(527);
                table.setLockedWidth(true);
                table.getDefaultCell().setFixedHeight(20);
                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                table.addCell(header);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(String.format("Page %d of", writer.getPageNumber()));
                PdfPCell cell = new PdfPCell(Image.getInstance(total));
                cell.setBorder(Rectangle.BOTTOM);
                table.addCell(cell);
                table.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
            }
            catch(DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }
 
        /**
         * Fills out the total number of pages before the document is closed.
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
         *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onCloseDocument(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                    2, 2, 0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Select date"));
        jPanel1.setName("Select date"); // NOI18N

        jDateChooser1.setDateFormatString("d MMMM yyyy");
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jLabel2.setText("Between");

        jLabel1.setText("To");

        jDateChooser2.setDateFormatString("d MMMM yyyy");
        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Select a faculty"));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FTMK", "FKP", "FKM", "FKEKK", "FKE", "FPTT", "FTK", "ALL" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        
    	Document document = new Document(PageSize.A4, 36, 36, 64, 36);
        
        //document.setMargins(30, 14, 50, 14);
        //String faculty = null;
        //jTextArea1.setText(""); //clear textarea
        Object selectedItem = jComboBox1.getSelectedItem();
		faculty = selectedItem.toString();
        if (selectedItem != null)
        {
		try {       
					long startTime = System.nanoTime(); 
                                        //Document document = new Document(PageSize.A4, 36, 36, 54, 36);
			        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ECSS_RPT_001.pdf"));
                                TableHeader event = new TableHeader();
                                writer.setPageEvent(event);
                                document.open(); 


		            //buat column plg banyak dulu untuk mudahkan design

		            PdfPCell cell;
	                
                    //jTextArea1.append(faculty);
                    
                    //initialize mysql con and var data type
                    Integer tot_by_fac = null;
                    ResultSet rs;
                    ResultSet rs_block;
                    ResultSet rs_code;
                    String query = null;
                    String remove_last_char;
                    String connectionURL;
                    String chapter_total_result = null;
                    String male_total_result = null;
                    String female_total_result = null;
                    
                    //initialize pdf
                    Font teks = new Font(Font.HELVETICA, 18, Font.BOLD);
                    Color orange = WebColors.getRGBColor("Orange");
                    Color magenta = WebColors.getRGBColor("#FF00FF");
                    Color cyan = WebColors.getRGBColor("#00FFFF");


                    connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=1234";
                    Connection conn = DriverManager.getConnection(connectionURL);
                    PreparedStatement st1 = conn.prepareStatement(connectionURL);
                    Statement st = conn.createStatement();
                    Statement st2 = conn.createStatement();
                    
                    
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
                            // Integer icd10_id_result = rs.getInt("Id");
                            //String icd10_name_result = rs.getString("name");

                        }

                                                String month = null;
                        String year = null;
                        String delimiter = " ";
                        String[] tarikh;
                        String date1 = null;
                        String date2 = null;
                        
                        java.util.Date d1 = jDateChooser1.getDate();
                        if (d1 == null) {
                            //System.out.println("No date specified!");
                        } else {

                            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            date1 = fmt.format(d1); //jdatechooser
   
                        }
                        
                        java.util.Date d2 = jDateChooser2.getDate();
                        if (d2 == null) {
                            //System.out.println("No date specified!");
                        } else {

                            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            date2 = fmt.format(d2); //jdatechooser
   
                        }

                        
                        if ("ALL".equals(faculty)){ //no prepared statement in this loop for faculty == ALL
                            

                            query = "SELECT COUNT(*) AS tot_by_fac FROM `lhr_diagnosis` ld, icd10_codes ic WHERE ic.icd10_code = ld.DiagnosisCd AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
                            rs = st.executeQuery(query);
                        
                        }else{ // prepared statement goes here for particular faculty
                            
                            query = "SELECT COUNT(*) AS tot_by_fac FROM `lhr_diagnosis` ld, icd10_codes ic WHERE `ld`.`LOCATION_CODE` = ? AND ic.icd10_code = ld.DiagnosisCd AND (Episode_date BETWEEN ? AND ?)";
                            st1 = conn.prepareStatement(query);
                            st1.setString(1, faculty);
                            st1.setString(2, date1);
                            st1.setString(3, date2);
                            rs = st1.executeQuery();
                        	
                        }
                        

                        
                        while (rs.next()) {
                            tot_by_fac = rs.getInt("tot_by_fac");
                        }

                        //jTextArea1.append("Total patient by faculty : " + tot_by_fac);
                        //jTextArea1.append("\nTotal patient by chapter : \n\n");
                        
                        PdfPTable header_table = new PdfPTable(2);
                        float[] columnWidths = {2f, 1.19f};
                        header_table.setWidths(columnWidths);
                        // 2 columns.
                        Image logo = Image.getInstance("logoUTeMPNG.png");
                        logo.scaleAbsolute(230, 100); 
                        //logo.scalePercent(7.3f);
                        
                        PdfPCell cell1 = new PdfPCell(logo);
                        cell1.setBorder(Rectangle.NO_BORDER);
                        //cell1.setLeading(15f, 0.3f);
                        header_table.addCell(cell1);
                        
                        PdfPCell cell2 = new PdfPCell(new Paragraph("Universiti Teknikal Malaysia Melaka\nHang Tuah Jaya, \n76100 Durian Tunggal, \nMelaka, Malaysia."));
                        cell2.setBorder(Rectangle.NO_BORDER);
                        //cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell2.setLeading(15f, 0.3f);
                        header_table.addCell(cell2);
                        
                        
                        
                        PdfPCell cell3 = new PdfPCell(new Paragraph("\nDiagnosis Report by Faculty", teks));
                        cell3.setBorder(Rectangle.NO_BORDER);
                        PdfPCell cell4 = new PdfPCell(new Paragraph("\n\n\n"));
                        cell4.setBorder(Rectangle.NO_BORDER);
                        header_table.addCell(cell3);
                        header_table.addCell(cell4);
                        
                        PdfPCell cell5 = new PdfPCell(new Paragraph("Faculty : " + faculty));
                        cell5.setBorder(Rectangle.NO_BORDER);
                        
                        String timeStamp = new SimpleDateFormat("dd-MM-yyyy h:mm a").format(Calendar.getInstance().getTime()); 
                        PdfPCell cell6 = new PdfPCell(new Paragraph("Date : " + timeStamp));
                        cell6.setBorder(Rectangle.NO_BORDER);
                        header_table.addCell(cell5);
                        header_table.addCell(cell6);
                        
                        PdfPCell cell8 = new PdfPCell(new Paragraph("Report ID : ECSS_RPT_001")); //remove with space and dash
                        cell8.setBorder(Rectangle.NO_BORDER);
                        header_table.addCell(cell8);
                        
                        PdfPCell cell7 = new PdfPCell(new Paragraph());
                        cell7.setBorder(Rectangle.NO_BORDER);
                        header_table.addCell(cell7);

                        document.add(header_table);
                        
                        //temp validation         
                              //Check for invalid data in DiagnosisCd column
                              query = "SELECT DiagnosisCd from lhr_diagnosis LEFT JOIN icd10_codes ON lhr_diagnosis.DiagnosisCd = icd10_codes.icd10_code WHERE icd10_code IS NULL AND LOCATION_CODE = ? AND (Episode_date BETWEEN ? AND ?)";
                              
                              st1 = conn.prepareStatement(query); //recreate statement
                              st1.setString(1, faculty); // set input parameter
                              st1.setString(2, date1);
                              st1.setString(3, date2);
                              rs = st1.executeQuery();
                              
                              while (rs.next()) {
                            	  invalid_record_list.add(rs.getString("DiagnosisCd")); //assign mysql result to list
                              }
                              
                        //document.add(new Phrase("\n\n\n\n\n\n\n\n\n"));
                        Paragraph paragraph1 = new Paragraph();
                        paragraph1.add("");
                        paragraph1.setSpacingAfter(12);
                        document.add(paragraph1);                              
                         
                        //Table header title 
                        PdfPTable table = new PdfPTable(5);
                        table.setWidths(new float[]{ 0.57f, 4f, 0.57f, 0.57f, 0.63f});
                        table.setLockedWidth(true);
                        table.setTotalWidth(document.right() - document.left());
                        cell = new PdfPCell(new Phrase("Chapter"));
                        cell.setRowspan(2);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Description"));
                        cell.setRowspan(2);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Gender"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setColspan(2);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Total"));
                        cell.setRowspan(2);
                        table.addCell(cell);
                        
                        table.addCell("M");
                        table.addCell("F");


                        document.add(table); 
                        //Table header title end 
                        
                        

                        
                        int i = 0;
                        int n = 0;
                        
                        Map<String, PdfPTable> reportObj = new HashMap<String, PdfPTable>();
                        
                        // only 1 chapter is needed with for loop
                        for (i = 1; i <= chapter_map.size(); i++){ // chapter_map.size() is total of keys during .put                               


                              //jTextArea1.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                              
                              
                              if ("ALL".equals(faculty)){ //no prepared statement in this loop for faculty == ALL
                            	  query = "SELECT COUNT(DiagnosisCd) AS count from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$'  AND substring(DiagnosisCd,1,2) = '"+ String.format("%02d", i) +"' AND ic.icd10_code = ld.DiagnosisCd AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
                                  rs = st.executeQuery(query);
                              
                              }else{ // prepared statement goes here for particular faculty
                            	  query = "SELECT COUNT(DiagnosisCd) AS count from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND substring(DiagnosisCd,1,2) = '"+ String.format("%02d", i) +"' AND LOCATION_CODE = ? AND (Episode_date BETWEEN ? AND ?)";
                                  st1 = conn.prepareStatement(query); //recreate statement
                                  st1.setString(1, faculty);
                                  st1.setString(2, date1);
                                  st1.setString(3, date2);
                                  rs = st1.executeQuery();
                              }
                              
                              

                              
                              while (rs.next()) {
                                  chapter_total_result = rs.getString("count");
                              }
      
                              //jTextArea1.append(String.format("%02d", i) + "   " + chapter_map.get(String.format("%02d", i)) + "   " + chapter_total_result);
                              
                              //System.out.format("Current cursor " +i + ": %f%n", writer.getVerticalPosition(true));
                              if (writer.getVerticalPosition(true) <= 90.000000) { //if chapter title is at the bottom then push it to new page. 112.000000 is title cursor for first page & 90.000000 is chapter title cursor other than 1st page.
                            	  document.newPage();
                              }
                          	
                              //chapter row
                              reportObj.put("chapter", new PdfPTable(5)); //declare new object. this object will be overwrite by new similar object name during the next loop
                              reportObj.get("chapter").getDefaultCell().setBorder(0);
                              reportObj.get("chapter").setWidths(new float[]{ 0.57f, 4f, 0.57f, 0.57f, 0.63f}); //guna float untuk precisekan column width
                              reportObj.get("chapter").setLockedWidth(true);
                              reportObj.get("chapter").setTotalWidth(document.right() - document.left());
                          	
                              //chapter row
                              cell = new PdfPCell(new Phrase(String.format("%02d", i)));
                              cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                              cell.setColspan(1);
                              cell.setBackgroundColor(orange);
                              reportObj.get("chapter").addCell(cell);
                              cell = new PdfPCell(new Phrase(chapter_map.get(String.format("%02d", i))));
                              cell.setBackgroundColor(orange);
                              cell.setColspan(1);
                              reportObj.get("chapter").addCell(cell);
                              
                              if ("ALL".equals(faculty)){ //no prepared statement in this loop for faculty == ALL
                            	  query = "SELECT COUNT(DiagnosisCd) AS COUNT, SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END) AS M, SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END) AS F FROM lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$'  AND ic.icd10_code = ld.DiagnosisCd AND substring(DiagnosisCd,1,2) = '" + String.format("%02d", i) + "' AND (Episode_date BETWEEN '\"+ date1 +\"' AND '\"+ date2 +\"')";
                                  rs = st.executeQuery(query);
                              
                              }else{ // prepared statement goes here for particular faculty
                            	  query = "SELECT SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END) AS M, SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END) AS F FROM lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND substring(DiagnosisCd,1,2) = '" + String.format("%02d", i) + "' AND LOCATION_CODE = ? GROUP BY LOCATION_CODE AND (Episode_date BETWEEN ? AND ?)";
                                  st1 = conn.prepareStatement(query); //recreate statement
                                  st1.setString(1, faculty);
                                  st1.setString(2, date1);
                                  st1.setString(3, date2);
                                  rs = st1.executeQuery();
                              }
                              
                              while (rs.next()) {
                                  male_total_result = rs.getString("M");
                                  female_total_result = rs.getString("F");
                                  System.out.println(male_total_result);
                              }
                              
                              cell = new PdfPCell(new Phrase(male_total_result));
                              cell.setBackgroundColor(orange);
                              cell.setColspan(1);
                              reportObj.get("chapter").addCell(cell);
                              
                              cell = new PdfPCell(new Phrase(female_total_result));
                              cell.setBackgroundColor(orange);
                              cell.setColspan(1);
                              reportObj.get("chapter").addCell(cell);                             
                              
                              cell = new PdfPCell(new Phrase(chapter_total_result));
                              cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                              cell.setBackgroundColor(orange);
                              cell.setColspan(1);
                              reportObj.get("chapter").addCell(cell);
                              
                              document.add(reportObj.get("chapter"));
                              
                              if (!"0".equals(chapter_total_result)){	// check chapter_total_result. if != 0 enter loop
                              
                                  //jTextArea1.append("\n\n\tTotal Patient by Block :");
                                  
                                  //block row     
                                  reportObj.put("block_title", new PdfPTable(6));
                                  reportObj.get("block_title").getDefaultCell().setBorder(0);
                                  reportObj.get("block_title").setWidths(new float[]{ 4.45f, 3, 3.5f, 3, 30, 5.9f});
                                  reportObj.get("block_title").setLockedWidth(true);
                                  reportObj.get("block_title").setTotalWidth(document.right() - document.left());
            	                  
                                  //block row
                                  cell = new PdfPCell(new Phrase(""));
                                  cell.setColspan(1);
                                  cell.setBorder(Rectangle.NO_BORDER); 
                                  reportObj.get("block_title").addCell(cell);
                                  cell = new PdfPCell(new Phrase("Total Patient by Block :"));
                                  cell.setColspan(6);
                                  cell.setBackgroundColor(magenta);
                                  reportObj.get("block_title").addCell(cell);                                 
                                  document.add(reportObj.get("block_title"));
                                  
                                  if ("ALL".equals(faculty)){
                                	  System.out.println(faculty);
                                	  query = "SELECT DiagnosisCd, idc, id, name, total FROM icd10_blocks, (SELECT DiagnosisCd, substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd group by substring(DiagnosisCd,3,3)) AS lolcat WHERE id = diag AND idc = '"+ String.format("%02d", i) +"' ";
                                	  rs_block = st.executeQuery(query);

                                  
                                  }else{
                                      query = "SELECT DiagnosisCd, idc, id, name, total FROM icd10_blocks, (SELECT DiagnosisCd, substring(DiagnosisCd,3,3) AS diag, count(*) as total from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND LOCATION_CODE = ? group by substring(DiagnosisCd,3,3)) AS lolcat WHERE id = diag AND idc = '"+ String.format("%02d", i) +"'";
                                      
                                      st1 = conn.prepareStatement(query); //recreate statement
                                      st1.setString(1, faculty); // set input parameter
                                      rs_block = st1.executeQuery();
                                	  
                                  }
                                  

                                  
                                                               
                                  
                                  while (rs_block.next()) {

                                      String block_id_result = rs_block.getString("id");
                                      String block_name_result = rs_block.getString("name");
                                      String block_total_result = rs_block.getString("total");
                                      //jTextArea1.append("\n\t" + block_id_result + "   " + block_name_result + "   "+ block_total_result +"\n");
                                     //jTextArea1.append("\n\t" + block_id_result + "   " + block_name_result + "   "+ block_total_result +"\n");
                                                                
                                      reportObj.put("block", new PdfPTable(6));
                                      
                                      reportObj.get("block").getDefaultCell().setBorder(0);
                                      reportObj.get("block").setWidths(new float[]{ 5.3f, 3.1f, 3.5f, 3, 38.5f, 5.9f});
                                      reportObj.get("block").setLockedWidth(true);
                                      reportObj.get("block").setTotalWidth(document.right() - document.left());	
                                      //System.out.println("loop block nombor :" + i);
                                      cell = new PdfPCell(new Phrase(""));
                                      cell.setColspan(1);
                                      cell.setBorder(Rectangle.NO_BORDER);
                                      reportObj.get("block").addCell(cell);
                                      cell = new PdfPCell(new Phrase(block_id_result));
                                      cell.setColspan(1);
                                      cell.setBackgroundColor(magenta);
                                      reportObj.get("block").addCell(cell);
                                      cell = new PdfPCell(new Phrase(block_name_result));
                                      cell.setColspan(3);
                                      cell.setBackgroundColor(magenta);
                                      reportObj.get("block").addCell(cell);
                                      cell = new PdfPCell(new Phrase(block_total_result));
                                      cell.setColspan(1);
                                      cell.setBackgroundColor(magenta);
                                      reportObj.get("block").addCell(cell);                                      
                                      document.add(reportObj.get("block"));

                                      remove_last_char = block_id_result.substring(0, block_id_result.length()-1); //remove last character in 'id' resultset retrieve from icd10_blocks table. A00 = A0

                                      //jTextArea1.append("\n\t\tTotal Patient by Code :"); 
                                      
                                      if(n != i){ //To make sure code title will be display only one time for every chapter. Check whether n == previous record of i. If not similar then display Code Title
                                    	  
                                    	  
                                          if (writer.getPageNumber() == 1 && writer.getVerticalPosition(true) <= 78.000000){ //This to check block records above for page 1
                                        	  //System.out.format("Current cursor " +i + ": %f%n", writer.getVerticalPosition(true));  
                                        	  document.newPage();                                      	  
                                          }
                                          
                                    	  
                                          if (writer.getPageNumber() !=1 && writer.getVerticalPosition(true) <= 74.000000) { ////This to check block records above other than page 1
                                        	  document.newPage();
                                          }
                                    	  
                                          // code row
                                          reportObj.put("code_title", new PdfPTable(6));
                                          reportObj.get("code_title").getDefaultCell().setBorder(0);
                                          reportObj.get("code_title").setWidths(new float[]{ 4.05f, 3, 3.5f, 3, 30, 5.9f});
                                          reportObj.get("code_title").setLockedWidth(true);
                                          reportObj.get("code_title").setTotalWidth(document.right() - document.left());	
                                          
                                          cell = new PdfPCell(new Phrase(""));
                                          cell.setColspan(2);
                                          cell.setBorder(Rectangle.NO_BORDER); 
                                          reportObj.get("code_title").addCell(cell);
                                          cell = new PdfPCell(new Phrase("Total Patient by Code :"));
                                          cell.setColspan(4);
                                          cell.setBackgroundColor(cyan);
                                          reportObj.get("code_title").addCell(cell);                                      
                                          document.add(reportObj.get("code_title"));
                                          n = i;
                                    	  
                                      }

                                      if ("ALL".equals(faculty)){
                                    	  query = "SELECT ld.diagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, ib.Id AS icd10_block, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_blocks ib, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,3,3) ='"+ block_id_result +"' AND ib.Id = '"+ block_id_result +"' AND ic.icd10_code = ld.DiagnosisCd  group by DiagnosisCd";
                                          rs_code = st1.executeQuery(query);
                                      
                                      }else{
                                          query = "SELECT ld.diagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, ib.Id AS icd10_block, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_blocks ib, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,3,3) ='"+ block_id_result +"' AND ib.Id = '"+ block_id_result +"' AND ic.icd10_code = ld.DiagnosisCd AND LOCATION_CODE = ? group by DiagnosisCd";
                                          
                                          st1 = conn.prepareStatement(query);
                                          st1.setString(1, faculty); // set input parameter
                                          rs_code = st1.executeQuery();
                                          //rs_code = st2.executeQuery(query);  
                                      }

                                      



                                      
                                      while (rs_code.next()) {
                                          
                                          String code_strip_result = rs_code.getString("icd10_code_strip");
                                          String code_desc_result = rs_code.getString("icd10_desc");
                                          String code_total_result = rs_code.getString("total");
                                          //jTextArea1.append("\n\t\t" + code_strip_result + "\t" + code_desc_result + "\t"+ code_total_result);
                                          //jTextArea1.append("\n\t\t" + code_strip_result + "\t" + code_desc_result + "\t"+ code_total_result);
                                          

                                          reportObj.put("code", new PdfPTable(6));
                                          //System.out.println("loop nombor :" + i);
                                          reportObj.get("code").getDefaultCell().setBorder(0);
                                          reportObj.get("code").setWidths(new float[]{ 4.55f, 3.9f, 4.5f, 3, 37.5f, 5.9f});
                                          reportObj.get("code").setLockedWidth(true);
                                          reportObj.get("code").setTotalWidth(document.right() - document.left());	
                                      

                                          cell = new PdfPCell(new Phrase(""));
                                          cell.setColspan(2);
                                          cell.setBorder(Rectangle.NO_BORDER); 
                                          reportObj.get("code").addCell(cell);
                                          cell = new PdfPCell(new Phrase(code_strip_result));
                                          cell.setColspan(1);
                                          cell.setBackgroundColor(cyan);
                                          reportObj.get("code").addCell(cell);
                                          cell = new PdfPCell(new Phrase(code_desc_result));
                                          cell.setColspan(2);
                                          cell.setBackgroundColor(cyan);
                                          reportObj.get("code").addCell(cell);
                                          cell = new PdfPCell(new Phrase(code_total_result));
                                          cell.setColspan(1);
                                          cell.setBackgroundColor(cyan);
                                          reportObj.get("code").addCell(cell);
                                          document.add(reportObj.get("code"));  
                                          
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

                        document.add(new Phrase("\nTotal Diagnosis: " + tot_by_fac));

                        if (invalid_record_list.size() != 0){
                        	document.add(new Phrase("\n"));
                        	document.add(new Phrase(invalid_record_list.size() + " invalid records founded in table `lhr_diagnosis`.`DiagnosisCd` : " + invalid_record_list));
                        }

                        long estimatedTime = System.nanoTime() - startTime;   
                        double seconds = (double)estimatedTime / 1000000000.0;
                        document.add(new Phrase("\nTime taken to generate this report : " + seconds + " seconds."));
                    }
                    catch (Exception e) {
                        System.err.println("Got an exception! ");
                        System.err.println(e.getMessage());
                    }
                    
                    
                    document.close();
                    
                    conn.close();
                    
                    // open pdf file platform independent
                    if (Desktop.isDesktopSupported()) {
                        try {
                            File myFile = new File("ECSS_RPT_001.pdf"); 
                            Desktop.getDesktop().open(myFile);
                        } catch (IOException ex) {
                            // no application registered for PDFs
                        }
                    }
                    
                }// if for select box end
                
                
                
                
                // jComboBox1ActionPerformed end bracket   

         catch (SQLException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
		 catch (Exception e) {// catch itextpdf exception
            e.printStackTrace();
         }
             
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed


    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:

        JDateChooser tarikh1 = (JDateChooser) evt.getSource();

        if ("date".equals(evt.getPropertyName())) {

            tarikh1.getDate();

            DateFormat fmt = new SimpleDateFormat("MM yyyy");
            String date = fmt.format(tarikh1.getDate()); //jdatechooser

            //jLabel2.setText(date);

            JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh1.getDateEditor();
            editor.setEditable(false);

        }

        JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh1.getDateEditor();
        editor.setEditable(false);

    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        // TODO add your handling code here:
        JDateChooser tarikh2 = (JDateChooser) evt.getSource();
        
        if ("date".equals(evt.getPropertyName())) {

            tarikh2.getDate();

            DateFormat fmt = new SimpleDateFormat("MM yyyy");
            String date = fmt.format(tarikh2.getDate()); //jdatechooser

            //jLabel2.setText(date);

            JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh2.getDateEditor();
            editor.setEditable(false);

        }

        JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh2.getDateEditor();
        editor.setEditable(false);
    }//GEN-LAST:event_jDateChooser2PropertyChange


    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    private static class grd {

        public grd() {
        }
    }
}
