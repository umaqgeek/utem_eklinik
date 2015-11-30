/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eklinik;
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
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.Desktop;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;
import main.RMIConnector;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.plaf.basic.CalendarHeaderHandler;
import org.jdesktop.swingx.plaf.basic.SpinningCalendarHeaderHandler;
import main.RMIConnector;


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
        jPanel3.setVisible(false);
        jPanel4.setVisible(false);

        pack();
        //jFrame1.getContentPane().setSize(800,400);
    }
    
    
        class TableHeader extends PdfPageEventHelper {
        /** The header text. */
        String header;
        String footer;
        /** The template with the total number of pages. */
        PdfTemplate total;
 
        /**
         * Allows us to change the content of the header.
         * @param header The new header String
         */
        public void setHeader(String header) {
            this.header = header;
        }

        
        
        public void setFooter(String footer) {
            this.footer = footer;
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
                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                table.addCell(footer);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(String.format("Page %d of", writer.getPageNumber()));
                PdfPCell cell = new PdfPCell(Image.getInstance(total));
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);
                table.writeSelectedRows(0, -1, 36, 55, writer.getDirectContent());

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Select date"));
        jPanel1.setName("Select date"); // NOI18N

        jDateChooser1.setDateFormatString("d MMMM yyyy");
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jLabel2.setText("From");

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
                .addContainerGap()
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Student Type"));

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setText("Local Student");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("Foreign Student");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton3)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton4)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Education Type"));

        buttonGroup3.add(jRadioButton7);
        jRadioButton7.setText("Master");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton8);
        jRadioButton8.setText("PHD");

        buttonGroup3.add(jRadioButton5);
        jRadioButton5.setText("Diploma");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton6);
        jRadioButton6.setText("Bachelor");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton9);
        jRadioButton9.setText("All");
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton5)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton6)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton7)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton8)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton9))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient Type"));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Staff");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Student");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        

                for (Enumeration<AbstractButton> buttons = buttonGroup1.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                button.getText();
                System.out.println(button.getText());
            }
        }
                
        jRadioButton1.setActionCommand("10");
        jRadioButton2.setActionCommand("5");  
        
        if(jRadioButton1.isSelected() == false && jRadioButton2.isSelected() == false){
            JOptionPane.showMessageDialog(null, "Patient type is required!");
        }else{
            System.out.println("Selected Radio Button: " + buttonGroup1.getSelection().getActionCommand());
        }

                

        //System.out.println(jRadioButton1.isSelected());
        
        //System.out.println("Selected Radio Button: " + jRadioButton1.isSelected());
        
        String month = null;
        String year = null;
        String delimiter = " ";
        String[] tarikh;
        String date1 = null;
        String date2 = null;
                        
        java.util.Date d1 = jDateChooser1.getDate();
        java.util.Date d2 = jDateChooser2.getDate();
        if (d1 == null || d2 == null) {
            JOptionPane.showMessageDialog(null, "Date is required!");
        } else {
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            date1 = fmt.format(d1); //jdatechooser
            date2 = fmt.format(d2); //jdatechooser
        }      
        

        Document document = new Document(PageSize.A4, 36, 36, 64, 36);
        
        //document.setMargins(30, 14, 50, 14);
        //String faculty = null;
        //jTextArea1.setText(""); //clear textarea
        Object selectedItem = jComboBox1.getSelectedItem();
        faculty = selectedItem.toString();
        if (d1 != null && d2 != null && selectedItem != null )
        {
		try {       
                    long startTime = System.nanoTime(); 

                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ECSS_RPT_001.pdf"));
                    TableHeader event = new TableHeader();
                    writer.setPageEvent(event);
                    document.open();    


                    //buat column plg banyak dulu untuk mudahkan design
                    PdfPCell cell;
	                
                    //jTextArea1.append(faculty);
                            
                    // call library
                    RMIConnector rc = new RMIConnector();
                    
                    // declaration host and port
                    String host = "biocore-stag.utem.edu.my";
                    int port = 1099; // for now, stick to this port
                    
                    //initialize mysql con and var data type
                    String tot_by_fac = null;
                    ResultSet rs = null;
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


                    //connectionURL = "jdbc:mysql://127.0.0.1/servercis?user=root&password=";
                    //Connection conn = DriverManager.getConnection(connectionURL);
                    //PreparedStatement st1 = conn.prepareStatement(connectionURL);
                    //Statement st = conn.createStatement();
                    //Statement st2 = conn.createStatement();
                    
                    
                    try {
                    	

                    	HashMap<String, String> chapter_map = new HashMap<>();
                        ArrayList<String> chapter_list = new ArrayList<String>();
                        ArrayList<String> invalid_record_output_list = new ArrayList<String>();
                        String sql = "SELECT * FROM `icd10_chapters` ORDER  BY `icd10_chapters`.`Id` ASC";
                        ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);
                        
                        //rs = st1.executeQuery(query);
                        
                        // view all data and results
                        for (int i = 0; i < data.size(); i++) {
                            chapter_map.put(data.get(i).get(0), data.get(i).get(1)); // data.get(i).get(0) = Id column. get(i).get(1) = name column
                        }
                        
                        /*
                        while (rs.next()) {
                            chapter_map.put(rs.getString("id"), rs.getString("name"));
                            chapter_list.add(rs.getString("id")); //assign mysql result to list
                            chapter_list.add(rs.getString("name")); //assign mysql result to list
                            // Integer icd10_id_result = rs.getInt("Id");
                            //String icd10_name_result = rs.getString("name");

                        }
                        */


   
                        
                        ArrayList<ArrayList<String>> tot_by_fac_list = null ;
                        
                        if ("ALL".equals(faculty)){ //no prepared statement in this loop for faculty == ALL
                            
                            sql = "SELECT COUNT(*) AS tot_by_fac FROM `lhr_diagnosis` ld, icd10_codes ic WHERE ic.icd10_code = ld.DiagnosisCd AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
/*
                            query = "SELECT COUNT(*) AS tot_by_fac FROM `lhr_diagnosis` ld, icd10_codes ic WHERE ic.icd10_code = ld.DiagnosisCd AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
                            rs = st.executeQuery(query);
                            */
                        
                        }else{ // prepared statement goes here for particular faculty
                            
                            sql = "SELECT COUNT(*) AS tot_by_fac FROM `lhr_diagnosis` ld, icd10_codes ic WHERE `ld`.`LOCATION_CODE` = '"+ faculty +"' AND ic.icd10_code = ld.DiagnosisCd AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
                            //query = "SELECT COUNT(*) AS tot_by_fac FROM `lhr_diagnosis` ld, icd10_codes ic WHERE `ld`.`LOCATION_CODE` = ? AND ic.icd10_code = ld.DiagnosisCd AND (Episode_date BETWEEN ? AND ?)";
                            /*
                            st1 = conn.prepareStatement(sql);
                            st1.setString(1, faculty);
                            st1.setString(2, date1);
                            st1.setString(3, date2);
                            rs = st1.executeQuery();
                            */
                        	
                        }
                        tot_by_fac_list = rc.getQuerySQL(host, port, sql);
                        //System.out.println("tot_by_fac_list "+ tot_by_fac_list);
                        
                        for (int i = 0; i < tot_by_fac_list.size(); i++) {
                            tot_by_fac = tot_by_fac_list.get(i).get(0); // get data from tot_by_fac column
                            //System.out.println(tot_by_fac);
                        }
                        

                        /*
                        while (rs.next()) {
                            tot_by_fac = rs.getInt("tot_by_fac");
                        }
                        */

                        //jTextArea1.append("Total patient by faculty : " + tot_by_fac);
                        //jTextArea1.append("\nTotal patient by chapter : \n\n");
                        
                        PdfPTable header_table1 = new PdfPTable(1);
                        float[] columnWidths = {3f};
                        header_table1.setWidths(columnWidths);

                        // 1 columns.
                        Image logo = Image.getInstance("logoUTeMPNG.png");
                        logo.scaleAbsolute(230, 100); 
                        //logo.scalePercent(7.3f);
                        
                        PdfPCell cell1 = new PdfPCell(logo);
                        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell1.setBorder(Rectangle.NO_BORDER);
                        //cell1.setLeading(15f, 0.3f);
                        header_table1.addCell(cell1);
                        

                        
                        DateFormat header_fmt = new SimpleDateFormat("dd-MM-yyyy");

                        PdfPCell cell3 = new PdfPCell(new Paragraph("\nDiagnosis Report by Faculty From " +header_fmt.format(d1)+ " To " +header_fmt.format(d2)+" \n", teks));
                        cell3.setBorder(Rectangle.NO_BORDER);
                        cell3.setColspan(2);
                        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
//                        PdfPCell cell4 = new PdfPCell(new Paragraph("\n\n\n"));
//                        cell4.setBorder(Rectangle.NO_BORDER);
                        header_table1.addCell(cell3);
//                        header_table.addCell(cell4);
                        document.add(header_table1);
                        
                        PdfPTable header_table = new PdfPTable(2);
                        header_table.setWidths(new float[]{1f, 1f});
                        header_table.setWidthPercentage(100);
                        
                        PdfPCell cell5 = new PdfPCell(new Paragraph("\nFaculty : " + faculty));
                        cell5.setBorder(Rectangle.NO_BORDER);
                        header_table.addCell(cell5);
                        
                        PdfPCell cell6 = new PdfPCell(new Paragraph("\nReport ID : ECSS_RPT_001"));
                        cell6.setBorder(Rectangle.NO_BORDER);      
                        header_table.addCell(cell6);
                        
                        PdfPCell cell8 = new PdfPCell(new Paragraph("")); //remove with space and dash
                        cell8.setBorder(Rectangle.NO_BORDER);
                        header_table.addCell(cell8);
                        
                        String timeStamp = new SimpleDateFormat("dd-MM-yyyy h:mm a").format(Calendar.getInstance().getTime()); 
                        PdfPCell cell7 = new PdfPCell(new Paragraph("Date : " + timeStamp));
                        cell7.setBorder(Rectangle.NO_BORDER);
                        header_table.addCell(cell7);

                        document.add(header_table);
                        
                        
                        ArrayList<ArrayList<String>> invalid_record_list = null ;
                        //ArrayList<String> invalid_record_list = new ArrayList<String>();
                        
                        //Check for invalid data in DiagnosisCd column
                        //query = "SELECT DiagnosisCd from lhr_diagnosis LEFT JOIN icd10_codes ON lhr_diagnosis.DiagnosisCd = icd10_codes.icd10_code WHERE icd10_code IS NULL AND LOCATION_CODE = ? AND (Episode_date BETWEEN ? AND ?)";
                        sql = "SELECT DiagnosisCd from lhr_diagnosis LEFT JOIN icd10_codes ON lhr_diagnosis.DiagnosisCd = icd10_codes.icd10_code WHERE icd10_code IS NULL AND LOCATION_CODE = '"+ faculty +"' AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";                            
                            
                        invalid_record_list = rc.getQuerySQL(host, port, sql);      
                             
                        /*
                              st1 = conn.prepareStatement(query); //recreate statement
                              st1.setString(1, faculty); // set input parameter
                              st1.setString(2, date1);
                              st1.setString(3, date2);
                              rs = st1.executeQuery();
                        */      
                        
                        /*
                        while (rs.next()) {
                            	  invalid_record_list.add(rs.getString("DiagnosisCd")); //assign mysql result to list
                        }
                        */
                        //System.out.println("invalid_record_list "+ invalid_record_list);
                        
                        for (int i = 0; i < invalid_record_list.size(); i++) {
                            invalid_record_list.get(i); // get data from tot_by_fac column
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
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(2);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Description"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(2);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Gender"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setColspan(2);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Total"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(2);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase("M"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("F"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);


                        document.add(table); 
                        //Table header title end 
                        
                        

                        
                        int i = 0;
                        int n = 0;
                        
                        Map<String, PdfPTable> reportObj = new HashMap<String, PdfPTable>();
                        
                        // only 1 chapter is needed with for loop
                        for (i = 1; i <= chapter_map.size(); i++){ // chapter_map.size() is total of keys during .put                               


                              //jTextArea1.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                              
                              ArrayList<ArrayList<String>> chapter_total_result_list = null ;  
                            
                              if ("ALL".equals(faculty)){ //no prepared statement in this loop for faculty == ALL
                                  
                                  sql = "SELECT COUNT(DiagnosisCd) AS count from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$'  AND substring(DiagnosisCd,1,2) = '"+ String.format("%02d", i) +"' AND ic.icd10_code = ld.DiagnosisCd AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"') LIMIT 1";

                                  
                            	  //query = "SELECT COUNT(DiagnosisCd) AS count from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$'  AND substring(DiagnosisCd,1,2) = '"+ String.format("%02d", i) +"' AND ic.icd10_code = ld.DiagnosisCd AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
                                  //rs = st.executeQuery(query);
                              
                              }else{ // prepared statement goes here for particular faculty
                                  sql = "SELECT COUNT(DiagnosisCd) AS count from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND substring(DiagnosisCd,1,2) = '"+ String.format("%02d", i) +"' AND LOCATION_CODE = '"+ faculty +"' AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
                            	  /*
                                  query = "SELECT COUNT(DiagnosisCd) AS count from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND substring(DiagnosisCd,1,2) = '"+ String.format("%02d", i) +"' AND LOCATION_CODE = ? AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
                                  
                                  st1 = conn.prepareStatement(query); //recreate statement
                                  st1.setString(1, faculty);
                                  rs = st1.executeQuery();
                                  */
                              }
                              
                              chapter_total_result_list = rc.getQuerySQL(host, port, sql); 
                              //System.out.println("chapter_total_result_list : " + chapter_total_result_list);

                              /*
                              while (rs.next()) {
                                  chapter_total_result = rs.getString("count");
                                   System.out.print(chapter_total_result);
                              }
                              */
                              
                              int num_i;

                              for (num_i = 0; num_i < chapter_total_result_list.size(); num_i++) {
                                //chapter_total_result_list.get(num_i); // get data from tot_by_fac column
                                //System.out.print(chapter_total_result_list.get(num_i).get(0));
                                //chapter_total_result_list.get(num_i);

                                //chapter_total_result = String.join(",", chapter_total_result_list.get(num_i)); // http://stackoverflow.com/a/23183963/894470 join() only works with Java 8
                                chapter_total_result = chapter_total_result_list.get(num_i).get(0);
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
                              //cell.setBorder(Rectangle.NO_BORDER);
                              //cell.setBackgroundColor(orange);
                              reportObj.get("chapter").addCell(cell);
                              cell = new PdfPCell(new Phrase(chapter_map.get(String.format("%02d", i))));
                              //cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                              //cell.setBackgroundColor(orange);
                              cell.setColspan(1);
                              //cell.setBorder(Rectangle.NO_BORDER);
                              reportObj.get("chapter").addCell(cell);
                              
                              if ("ALL".equals(faculty)){ //no prepared statement in this loop for faculty == ALL
                                  sql = "SELECT COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F FROM lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$'  AND ic.icd10_code = ld.DiagnosisCd AND substring(DiagnosisCd,1,2) = '" + String.format("%02d", i) + "' AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
                            	  //query = "SELECT COUNT(DiagnosisCd) AS COUNT, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F FROM lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$'  AND ic.icd10_code = ld.DiagnosisCd AND substring(DiagnosisCd,1,2) = '" + String.format("%02d", i) + "' AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
                                  //rs = st.executeQuery(query);
                              
                              }else{ // use coalese otherwise column will return null, Refer : http://stackoverflow.com/questions/7602271/how-do-i-get-sum-function-in-mysql-to-return-0-if-no-values-are-found
                            	  
                                  sql = "SELECT COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F FROM lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND substring(DiagnosisCd,1,2) = '" + String.format("%02d", i) + "' AND LOCATION_CODE = '"+ faculty +"' AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
                                  /*
                                  query = "SELECT COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F FROM lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND substring(DiagnosisCd,1,2) = '" + String.format("%02d", i) + "' AND LOCATION_CODE = ? AND (Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"')";
                                  st1 = conn.prepareStatement(query); //recreate statement
                                  st1.setString(1, faculty);
                                  rs = st1.executeQuery();
                                  */
                              }
                              
                              ArrayList<ArrayList<String>> gender_total_result_list = null ;  
                              gender_total_result_list = rc.getQuerySQL(host, port, sql); 
                              //System.out.println("gender_total_result_list "+ gender_total_result_list);
                              
                              for (int gender_i = 0; gender_i < chapter_total_result_list.size(); gender_i++) {

                                male_total_result = gender_total_result_list.get(gender_i).get(0);
                                female_total_result = gender_total_result_list.get(gender_i).get(1);
                              }
                              
                              /*
                              while (rs.next()) {
                                  male_total_result = rs.getString("M");
                                  female_total_result = rs.getString("F");
                                  //System.out.println(male_total_result);
                              }
                              */
                              
                              cell = new PdfPCell(new Phrase(male_total_result));
                              cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                              //cell.setBackgroundColor(orange);
                              cell.setColspan(1);
                              //cell.setBorder(Rectangle.NO_BORDER);
                              reportObj.get("chapter").addCell(cell);
                              
                              cell = new PdfPCell(new Phrase(female_total_result));
                              cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                              //cell.setBackgroundColor(orange);
                              cell.setColspan(1);
                              //cell.setBorder(Rectangle.NO_BORDER);
                              reportObj.get("chapter").addCell(cell);                             
                              
                              cell = new PdfPCell(new Phrase(chapter_total_result));
                              cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                              //cell.setBackgroundColor(orange);
                              cell.setColspan(1);
                              //cell.setBorder(Rectangle.NO_BORDER);
                              reportObj.get("chapter").addCell(cell);
                              
                              document.add(reportObj.get("chapter"));
                              
                              if (!"0".equals(chapter_total_result)){	// check chapter_total_result. if != 0 enter loop
                              
                                  //jTextArea1.append("\n\n\tTotal Patient by Block :");
                                  
                                  //block row     
                            	  /*
                                  reportObj.put("block_title", new PdfPTable(6));
                                  reportObj.get("block_title").getDefaultCell().setBorder(0);
                                  reportObj.get("block_title").setWidths(new float[]{4.47f, 3, 3.5f, 3, 30, 5.9f});
                                  reportObj.get("block_title").setLockedWidth(true);
                                  reportObj.get("block_title").setTotalWidth(document.right() - document.left());
            	                  
                                  //block row
                                  
                                  cell = new PdfPCell(new Phrase(""));
                                  cell.setColspan(1);
                                  cell.setBorder(Rectangle.NO_BORDER); 
                                  reportObj.get("block_title").addCell(cell);
                                  cell = new PdfPCell(new Phrase("Total Patient by Block :"));
                                  cell.setColspan(6);
                                  //cell.setBackgroundColor(magenta);
                                  reportObj.get("block_title").addCell(cell);                                 
                                  document.add(reportObj.get("block_title"));
                                  */
                                  
                                  if ("ALL".equals(faculty)){
                                	  //System.out.println(faculty); 
                                      
                                      sql = "SELECT DiagnosisCd, idc, id, name, M, F, total FROM icd10_blocks, (SELECT DiagnosisCd, substring(DiagnosisCd,3,3) AS diag, count(*) as total, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND (ld.Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"') group by substring(DiagnosisCd,3,3)) AS lolcat WHERE id = diag AND idc = '"+ String.format("%02d", i) +"'";
                                      /*
                                        query = "SELECT DiagnosisCd, idc, id, name, total, M, F FROM icd10_blocks, (SELECT DiagnosisCd, substring(DiagnosisCd,3,3) AS diag, count(*) as total, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND (ld.Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"') group by substring(DiagnosisCd,3,3)) AS lolcat WHERE id = diag AND idc = '"+ String.format("%02d", i) +"'";
                                        rs_block = st.executeQuery(query);
                                      */
                                  
                                  }else{
                                      
                                      sql = "SELECT DiagnosisCd, idc, id, name, M, F, total FROM icd10_blocks, (SELECT DiagnosisCd, substring(DiagnosisCd,3,3) AS diag, count(*) as total, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND LOCATION_CODE = '"+ faculty +"' AND (ld.Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"') group by substring(DiagnosisCd,3,3)) AS lolcat WHERE id = diag AND idc = '"+ String.format("%02d", i) +"'";
                                      /*
                                      query = "SELECT DiagnosisCd, idc, id, name, total, M, F FROM icd10_blocks, (SELECT DiagnosisCd, substring(DiagnosisCd,3,3) AS diag, count(*) as total, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F from lhr_diagnosis ld, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND ic.icd10_code = ld.DiagnosisCd AND LOCATION_CODE = ? AND (ld.Episode_date BETWEEN '"+ date1 +"' AND '"+ date2 +"') group by substring(DiagnosisCd,3,3)) AS lolcat WHERE id = diag AND idc = '"+ String.format("%02d", i) +"'";
                                      
                                      st1 = conn.prepareStatement(query); //recreate statement
                                      st1.setString(1, faculty); // set input parameter
                                      rs_block = st1.executeQuery();
                                      */                               	  
                                  }
                                  
                              ArrayList<ArrayList<String>> block_total_result_list = null ;  
                              block_total_result_list = rc.getQuerySQL(host, port, sql); 
                              //System.out.println("block_total_result_list" + block_total_result_list);
                              
                              for (int block_i = 0; block_i < block_total_result_list.size(); block_i++) {
                              //while (rs_block.next()) {

                                String block_id_result = block_total_result_list.get(block_i).get(2);
                                String block_name_result = block_total_result_list.get(block_i).get(3);
                                String block_male_total_result = block_total_result_list.get(block_i).get(4);
                                String block_female_total_result = block_total_result_list.get(block_i).get(5);
                                String block_total_result = block_total_result_list.get(block_i).get(6);
                                  

                             
                                /*
                                      String block_id_result = rs_block.getString("id");
                                      String block_name_result = rs_block.getString("name");
                                      String block_male_total_result = rs_block.getString("M");
                                      String block_female_total_result = rs_block.getString("F");
                                      String block_total_result = rs_block.getString("total");
                                */
                                      //jTextArea1.append("\n\t" + block_id_result + "   " + block_name_result + "   "+ block_total_result +"\n");
                                     //jTextArea1.append("\n\t" + block_id_result + "   " + block_name_result + "   "+ block_total_result +"\n");
                                                                
                                      reportObj.put("block", new PdfPTable(7));
                                      
                                      reportObj.get("block").getDefaultCell().setBorder(0);
                                      reportObj.get("block").setWidths(new float[]{ 0.530f, 0.31f, 1.55f, 1.87f,  0.53f, 0.53f, 0.59f});
                                      reportObj.get("block").setLockedWidth(true);
                                      reportObj.get("block").setTotalWidth(document.right() - document.left());	
                                      //System.out.println("loop block nombor :" + i);
                                      cell = new PdfPCell(new Phrase(""));
                                      cell.setColspan(1);
                                      cell.setBorder(Rectangle.NO_BORDER);
                                      reportObj.get("block").addCell(cell);
                                      cell = new PdfPCell(new Phrase(block_id_result));
                                      cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                                      cell.setColspan(1);
                                      //cell.setBorder(Rectangle.NO_BORDER);
                                      //cell.setBackgroundColor(magenta);
                                      reportObj.get("block").addCell(cell);
                                      cell = new PdfPCell(new Phrase(block_name_result));
                                      cell.setColspan(2);
                                      //cell.setBorder(Rectangle.NO_BORDER);
                                      //cell.setBackgroundColor(magenta);
                                      reportObj.get("block").addCell(cell);
                                      
                                      cell = new PdfPCell(new Phrase(block_male_total_result));
                                      cell.setColspan(1);
                                      cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                                      //cell.setBorder(Rectangle.NO_BORDER);
                                      reportObj.get("block").addCell(cell);
                      
                                      cell = new PdfPCell(new Phrase(block_female_total_result));
                                      cell.setColspan(1);
                                      cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                                      //cell.setBorder(Rectangle.NO_BORDER);
                                      reportObj.get("block").addCell(cell);
                                      
                                      
                                      cell = new PdfPCell(new Phrase(block_total_result));
                                      cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                                      cell.setColspan(1);
                                      //cell.setBackgroundColor(magenta);
                                      //cell.setBorder(Rectangle.NO_BORDER);
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
                                          /*
                                          reportObj.put("code_title", new PdfPTable(6));
                                          reportObj.get("code_title").getDefaultCell().setBorder(0);
                                          reportObj.get("code_title").setWidths(new float[]{ 4.0f, 3, 3.5f, 3, 30, 5.9f});
                                          reportObj.get("code_title").setLockedWidth(true);
                                          reportObj.get("code_title").setTotalWidth(document.right() - document.left());	
                                          
                                          
                                          cell = new PdfPCell(new Phrase(""));
                                          cell.setColspan(2);
                                          cell.setBorder(Rectangle.NO_BORDER); 
                                          reportObj.get("code_title").addCell(cell);
                                          cell = new PdfPCell(new Phrase("Total Patient by Code :"));
                                          cell.setColspan(4);
                                          //cell.setBackgroundColor(cyan);
                                          reportObj.get("code_title").addCell(cell);                                      
                                          document.add(reportObj.get("code_title"));
                                          n = i;
                                          */
                                    	  
                                      }
                                      
                                      //System.out.println("block_id_result: " + block_id_result);

                                      if ("ALL".equals(faculty)){
                                          sql = "SELECT ld.diagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, ib.Id AS icd10_block, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_blocks ib, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,3,3) ='"+ block_id_result +"' AND ib.Id = '"+ block_id_result +"' AND ic.icd10_code = ld.DiagnosisCd AND (ld.Episode_date BETWEEN '"+date1+"' AND '"+date2+"') group by DiagnosisCd";

                                    	  //query = "SELECT ld.diagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, ib.Id AS icd10_block, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_blocks ib, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,3,3) ='"+ block_id_result +"' AND ib.Id = '"+ block_id_result +"' AND ic.icd10_code = ld.DiagnosisCd AND (ld.Episode_date BETWEEN '"+date1+"' AND '"+date2+"') group by DiagnosisCd";
                                          //rs_code = st1.executeQuery(query);
                                      
                                      }else{
                                          sql = "SELECT ld.diagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, ib.Id AS icd10_block, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_blocks ib, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,3,3) ='"+ block_id_result +"' AND ib.Id ='"+ block_id_result +"' AND ic.icd10_code = ld.DiagnosisCd AND LOCATION_CODE = '"+faculty+"' AND (ld.Episode_date BETWEEN '"+date1+"' AND '"+date2+"') group by DiagnosisCd";
                                          /*
                                          query = "SELECT ld.diagnosisCd, substring(DiagnosisCd,6,5) as icd10_code_strip, ic.icd10_desc, ib.Id AS icd10_block, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS M, COALESCE(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS F, COUNT(DiagnosisCd) as total from lhr_diagnosis ld, icd10_blocks ib, icd10_codes ic WHERE DiagnosisCd REGEXP '^[a-zA-Z0-9]+$' AND substring(DiagnosisCd,3,3) ='"+ block_id_result +"' AND ib.Id ='"+ block_id_result +"' AND ic.icd10_code = ld.DiagnosisCd AND LOCATION_CODE = ? AND (ld.Episode_date BETWEEN '"+date1+"' AND '"+date2+"') group by DiagnosisCd";
                                          
                                          st1 = conn.prepareStatement(query);
                                          st1.setString(1, faculty); // set input parameter
                                          rs_code = st1.executeQuery();
                                          System.out.println(block_id_result);
                                          */
                                      }
                                      
                                      ArrayList<ArrayList<String>> code_total_result_list = null ;  
                                      code_total_result_list = rc.getQuerySQL(host, port, sql); 
                                      //System.out.println("code_total_result_list" + code_total_result_list);

                                      for (int code_i = 0; code_i < code_total_result_list.size(); code_i++) {
                                      //while (rs_code.next()) {
                                          
                                          String code_strip_result = code_total_result_list.get(code_i).get(1);
                                          String code_desc_result = code_total_result_list.get(code_i).get(2);
                                          String code_male_total_result = code_total_result_list.get(code_i).get(4);
                                          String code_female_total_result = code_total_result_list.get(code_i).get(5);
                                          String code_total_result = code_total_result_list.get(code_i).get(6);
                                          
                                          //String code_strip_result = rs_code.getString("icd10_code_strip");
                                          //String code_desc_result = rs_code.getString("icd10_desc");
                                          //String code_male_total_result = rs_code.getString("M");
                                          //String code_female_total_result = rs_code.getString("F");
                                          //String code_total_result = rs_code.getString("total");
                                          //jTextArea1.append("\n\t\t" + code_strip_result + "\t" + code_desc_result + "\t"+ code_total_result);
                                          //jTextArea1.append("\n\t\t" + code_strip_result + "\t" + code_desc_result + "\t"+ code_total_result);
      

                                          reportObj.put("code", new PdfPTable(8));
                                          //System.out.println("loop nombor :" + i);
                                          reportObj.get("code").getDefaultCell().setBorder(0);
                                          reportObj.get("code").setWidths(new float[]{ 0.49f, 0.48f, 0.54f, 1.70f, 1.69f, 0.61f, 0.61f, 0.68f});
                                          reportObj.get("code").setLockedWidth(true);
                                          reportObj.get("code").setTotalWidth(document.right() - document.left());	
                                      

                                          cell = new PdfPCell(new Phrase(""));
                                          cell.setColspan(2);
                                          cell.setBorder(Rectangle.NO_BORDER); 
                                          reportObj.get("code").addCell(cell);
                                          cell = new PdfPCell(new Phrase(code_strip_result));
                                          cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                                          cell.setColspan(1);
                                          //cell.setBorder(Rectangle.NO_BORDER);
                                          //cell.setBackgroundColor(cyan);
                                          reportObj.get("code").addCell(cell);
                                          cell = new PdfPCell(new Phrase(code_desc_result));        
                                          cell.setColspan(2);
                                          //cell.setBackgroundColor(cyan);
                                          //cell.setBorder(Rectangle.NO_BORDER);
                                          reportObj.get("code").addCell(cell);
                                          
                                          cell = new PdfPCell(new Phrase(code_male_total_result));
                                          cell.setColspan(1);
                                          cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                                          //cell.setBorder(Rectangle.NO_BORDER);
                                          reportObj.get("code").addCell(cell);

                                          cell = new PdfPCell(new Phrase(code_female_total_result));
                                          cell.setColspan(1);
                                          cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                                          //cell.setBorder(Rectangle.NO_BORDER);
                                          reportObj.get("code").addCell(cell);   
                                          
                                          cell = new PdfPCell(new Phrase(code_total_result));
                                          cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
                                          cell.setColspan(1);
                                          //cell.setBackgroundColor(cyan);
                                          //cell.setBorder(Rectangle.NO_BORDER);
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
                        //document.add(new Phrase("\nTime taken to generate this report : " + seconds + " seconds."));
                    }
                    catch (Exception e) {
                        System.err.println("Got an exception! ");
                        System.err.println(e.getMessage());
                    }
                    //-----------------------start
               //document.add(new Paragraph("\n\n\n-End of Report-"));
               Font teks1 = new Font(Font.BOLD);
                Paragraph paragraph = new Paragraph();
			paragraph.add("\n\n\n-End of Report-");
			paragraph.setAlignment(Element.ALIGN_CENTER);
                        paragraph.setFont(teks1);
                       
//                        Font font = new Font(Font.COURIER);
//			font.setStyle(Font.BOLD);
			document.add(paragraph);
               
                //------------------------
                    
                    document.close();
                    
                    //conn.close();
                    
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
         /*
         catch (SQLException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
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

    
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        jPanel3.setVisible(true);
        
        //Dimension windowSize = getContentPane().getSize();
        //System.out.println(windowSize);
        pack();

//setPreferredSize(new Dimension(84,49)) ;
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        jPanel3.setVisible(false);
        jPanel4.setVisible(false);
        
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        pack();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        jPanel4.setVisible(true);
        pack();

    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        jPanel4.setVisible(true);
        pack();
        
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton9ActionPerformed


    
    
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    // End of variables declaration//GEN-END:variables

    private static class grd {

        public grd() {
        }
    }
}
