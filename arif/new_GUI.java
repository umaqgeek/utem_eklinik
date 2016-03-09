/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_new;

import com.itextpdf.text.BaseColor;
import java.sql.*;
import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Desktop;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import main.RMIConnector;

public class new_GUI extends javax.swing.JFrame {
    
String Faculty = null;
String[] tarikh;
String date1 = null;
String date2 = null;
String query = null;
RMIConnector rc = new RMIConnector();
String host = "biocore-stag.utem.edu.my";
int port = 1099; // for now, stick to this port  
String sql=null;
    
    public new_GUI() {
        initComponents();
    }
    class TableHeader extends PdfPageEventHelper 
    {
        String header;
        String footer;
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
        public void onOpenDocument(PdfWriter writer, Document reportpdf) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }
        @Override
        public void onEndPage(PdfWriter writer, Document reportpdf) {
            PdfPTable table = new PdfPTable(3);
            try {
                table.setWidths(new int[]{24, 24, 2});
                table.setTotalWidth(527);
                table.setLockedWidth(true);
                table.getDefaultCell().setFixedHeight(20);
                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
           //   table.addCell(footer);
            //  table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            //  table.addCell(String.format("Page %d of  ",writer.getPageNumber()));
            // PdfPCell cell = new PdfPCell(Image.getInstance(total));
            //  cell.setBorder(Rectangle.NO_BORDER);
           //  table.addCell(cell);
              
             //table.writeSelectedRows(0, -1, 36, 55, writer.getDirectContent())
                //-----------------------
                 PdfContentByte cb = writer.getDirectContent();
                //header content
                String headerContent = " ";
                //header content
                String footerContent = headerContent;
                /*
                 * Foooter
                 */
               ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(String.format("%d ", writer.getPageNumber())), 
                       reportpdf.right()- 12 , reportpdf.bottom() - 20, 0);
            //ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(String.format("Page %d of", writer.getPageNumber())), 
                  // reportpdf.right() - 12 , reportpdf.bottom() - 20, 0);
            // ColumnText.showTextAligned(total, Element.ALIGN_RIGHT, new Phrase(String.valueOf(writer.getPageSize())), 
                    // reportpdf.right() - 2 , reportpdf.bottom() - 20, 0);
                //-----------------------
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
        @Override
        public void onCloseDocument(PdfWriter writer, Document reportpdf) {
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

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Select a faculty:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Please select-", "ALL", "FTMK", "FKP", "FKM", "FKEKK", "FKE", "FPTT", "FTK" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("From:");

        jLabel3.setText("To:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(11, 11, 11)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap(219, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) 
    {
        JDateChooser tarikh1 = (JDateChooser) evt.getSource();
        if ("date".equals(evt.getPropertyName())) {
            tarikh1.getDate();
            DateFormat fmt = new SimpleDateFormat("MM yyyy");
            String date = fmt.format(tarikh1.getDate()); //jdatechooser
            JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh1.getDateEditor();
            editor.setEditable(false);
        }
        JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh1.getDateEditor();
        editor.setEditable(false);   
    }
   
    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange

        JDateChooser tarikh2 = (JDateChooser) evt.getSource();
        if ("date".equals(evt.getPropertyName())) {
            tarikh2.getDate();
            DateFormat fmt = new SimpleDateFormat("MM yyyy");
            String date = fmt.format(tarikh2.getDate()); //jdatechooser
            JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh2.getDateEditor();
            editor.setEditable(false);
        }
        JTextFieldDateEditor editor = (JTextFieldDateEditor) tarikh2.getDateEditor();
        editor.setEditable(false);
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
 
        java.util.Date d1 = jDateChooser1.getDate();
                        if (d1 == null) {
                            JOptionPane.showMessageDialog(null, "Date is required!");
                        } else {
                            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            date1 = fmt.format(d1); //jdatechooser
                        } 
                        java.util.Date d2 = jDateChooser2.getDate();
                        if (d2 == null) {
                            JOptionPane.showMessageDialog(null, "Date is required!");
                        } else {
                            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            date2 = fmt.format(d2); //jdatechooser
                        }
                        
        Object selectedItem = jComboBox1.getSelectedItem();
        Faculty = selectedItem.toString();
        if ((date1 != null && date2!= null) && selectedItem != "-Please select-")
        {
        try
        { 
             Document reportpdf = new Document(PageSize.A4);
             TableHeader event = new TableHeader();   
             PdfWriter writer = PdfWriter.getInstance (reportpdf, new FileOutputStream("C:\\Users\\acer\\Desktop\\Report" + Faculty + ".pdf" )  );
             writer .setPageEvent(event);
             reportpdf.open(); 
             PdfPTable header = new PdfPTable(2);
             float[] columnWidths = {2f, 1.19f};
             header.setWidths(columnWidths);
             Image logo = Image.getInstance("logo.png" );
             logo.scaleAbsolute(230, 100);
             PdfPCell cell1 = new PdfPCell(logo);
             cell1.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell1);
             PdfPCell cell2 = new PdfPCell(new Paragraph("Universiti Teknikal Malaysia Melaka\nHang Tuah Jaya, \n76100 Durian Tunggal, \nMelaka, Malaysia."));
             cell2.setBorder(Rectangle.NO_BORDER);
             cell2.setLeading(15f, 0.3f);
             header.addCell(cell2);
             Font teks = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
             PdfPCell cell3 = new PdfPCell(new Paragraph("\nSymptom Report by Faculty", teks));
             cell3.setBorder(Rectangle.NO_BORDER);
             PdfPCell cell4 = new PdfPCell(new Paragraph("\n\n\n"));
             cell4.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell3);
             header.addCell(cell4);
             PdfPCell cell5 = new PdfPCell(new Paragraph("Faculty : " + Faculty));
             cell5.setBorder(Rectangle.NO_BORDER);
             String timeStamp = new SimpleDateFormat("dd-MM-yyyy h:mm a").format(Calendar.getInstance().getTime()); 
             PdfPCell cell6 = new PdfPCell(new Paragraph("Date : " + timeStamp));
             cell6.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell5);
             header.addCell(cell6);
             PdfPCell cell7 = new PdfPCell(new Paragraph( "From :  "+date1+" To : "+date2+" "  ));
             cell7.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell7);     
             PdfPCell cell8 = new PdfPCell(new Paragraph("Report ID : ECSS RPT 003\n\n"));
             cell8.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell8);
      reportpdf.add(header);
     try {
                 PdfPTable reportpdftable = new PdfPTable(4);
                 reportpdftable.setWidths(new int[]{3,2,2,2});
                 PdfPCell reportpdf_cell;
                 Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
                 Font font1 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
                 String description = ("SYMPTOM NAME");
                 reportpdf_cell=new PdfPCell(new Phrase(description,font));
                 reportpdf_cell.setRowspan(2);
                 reportpdf_cell.setBackgroundColor(new BaseColor(0,121,182));
                 reportpdftable.addCell(reportpdf_cell);
                 String PERSON_STATUS = ("GENDER");
                 reportpdf_cell=new PdfPCell(new Phrase(PERSON_STATUS,font));
                 reportpdf_cell.setBackgroundColor(new BaseColor(0,121,182));
                 reportpdf_cell.setColspan(2);
                 reportpdftable.addCell(reportpdf_cell);
                 String total_patient = ("TOTAL ");
                 reportpdf_cell=new PdfPCell(new Phrase(total_patient,font));
                 reportpdf_cell.setBackgroundColor(new BaseColor(0,121,182));
                 reportpdf_cell.setRowspan(2);
                 reportpdftable.addCell(reportpdf_cell);
                 reportpdf_cell=new PdfPCell(new Phrase("Male",font));
                 reportpdf_cell.setBackgroundColor(new BaseColor(0,121,182));
                 reportpdftable.addCell(reportpdf_cell);
                 reportpdf_cell=new PdfPCell(new Phrase("Female",font));
                 reportpdf_cell.setBackgroundColor(new BaseColor(0,121,182));
                 reportpdftable.addCell(reportpdf_cell);
                 
                 if("ALL".equals(Faculty ))
                 {  
                   sql = "select coalesce(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS Male,coalesce(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS Female, Signs_desc ,  count(*) as Bilangan_pelajar from lhr_signs  where (Episode_date between '"+ date1 +"' AND '"+ date2 +"') group by Signs_desc  ORDER BY `Signs_desc` ASC ";     
                 }
                 else
                 {
                    sql="select coalesce( SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS Male,coalesce(SUM(CASE WHEN PERSON_STATUS = 'P' THEN 1 ELSE 0 END),0) AS Female , Signs_desc,count(*) as Bilangan_pelajar from lhr_signs where LOCATION_CODE= '"+Faculty+"'  and (Episode_date between '"+ date1 +"' AND '"+ date2 +"')  group by Signs_desc ORDER BY `Signs_desc` ASC ";
                 }
                 
                 ArrayList<ArrayList<String>> by_fac = rc.getQuerySQL(host, port, sql) ;  
                             
                 for (ArrayList<String> by_fac1 : by_fac) {
                     reportpdf_cell = new PdfPCell(new Phrase(by_fac1.get(2)));
                     reportpdftable.addCell(reportpdf_cell);
                     reportpdf_cell = new PdfPCell(new Phrase(by_fac1.get(0)));
                     reportpdftable.addCell(reportpdf_cell);
                     reportpdf_cell = new PdfPCell(new Phrase(by_fac1.get(1)));
                     reportpdftable.addCell(reportpdf_cell);
                     reportpdf_cell = new PdfPCell(new Phrase(by_fac1.get(3)));
                     reportpdftable.addCell(reportpdf_cell);
                 }
            reportpdf.add(reportpdftable);      
 
             try {
               
                 if("ALL".equals(Faculty ))
                 {
                    sql = "select count(*)  Signs_desc from lhr_signs where (Episode_date between '"+ date1 +"' AND '"+ date2 +"')  ";    
                 }
                 else
                 {
            sql= "select count(*)  Signs_desc from lhr_signs where location_code= '" +Faculty+"' and (Episode_date between '"+ date1 +"' AND '"+ date2 +"')   group by Location_code     " ;
                 }    
            {
                  ArrayList<ArrayList<String>> total = rc.getQuerySQL(host, port, sql) ;  
                 for (int i = 0; i < total.size(); i++) {
                String Total = ( total.get(i).get(0)  );
                PdfPTable footer = new PdfPTable(1);
                PdfPCell cell9 = new PdfPCell(new Paragraph("\n\n\nTotal patient from  "+ Faculty +" = "+ Total + "")); //remove with space and dash
                cell9.setBorder(Rectangle.NO_BORDER);
                footer.addCell(cell9);
                reportpdf.add(footer);
                 }  
            }
            Paragraph p = new Paragraph("\n\n--End of report-- \n\n", new Font(FontFamily.HELVETICA, 14));
       p.setAlignment(Element.ALIGN_CENTER);
        reportpdf.add(p);    
reportpdf.newPage();         
 reportpdf.close();
  if (Desktop.isDesktopSupported())
         {
                try {
                    File myFile = new File("C:\\Users\\acer\\Desktop\\Report" + Faculty + ".pdf");
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
           }
             }   
          }
         catch ( DocumentException e)
         {
             System.err.println("Got an exception!  ");
             System.err.println(e.getMessage());
         }
        }
         catch (DocumentException e)
         {
             System.err.println("Got an exception!  ");
             System.err.println(e.getMessage());
         }     
        } 
        catch (DocumentException | IOException e)
            {
                System.err.println("Got an exception!  ");
                System.err.println(e.getMessage());
            }
        }   
    }//GEN-LAST:event_jComboBox1ActionPerformed
     

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws com.itextpdf.text.DocumentException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String args[])  throws SQLException, DocumentException, FileNotFoundException  {
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
            java.util.logging.Logger.getLogger(new_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(new_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(new_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(new_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new new_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
