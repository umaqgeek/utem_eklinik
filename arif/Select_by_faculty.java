/**
 *
 * @author arif
 */

package GUI;

import com.itextpdf.text.DocumentException;
import java.sql.*;
import java.util.ArrayList.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import java.awt.Desktop;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;

public class Select_by_faculty extends javax.swing.JFrame {

    
    public Select_by_faculty() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Generate report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


        try
        {
        String connectionURL = "jdbc:mysql://10.73.32.200 /servercis?user=root&password=qwerty";
        Connection myconn = DriverManager.getConnection(connectionURL);
         
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        String query0 =" Select distinct location_code from lhr_signs";
        Statement st0=myconn.createStatement();
        ResultSet rs0 = st0.executeQuery(query0);
        while (rs0.next())
        {
            String location_code = rs0.getString("Location_code");
            String[] Faculty = { location_code };
        {
       
        //String[] Faculty = {"FTMK","FKEKK","FKE","FKP","FTK","FPTT","FKM" };

for(int i=0; i<Faculty.length; i++)
{
    Document reportpdf = new Document(PageSize.A4);
    PdfWriter.getInstance (reportpdf, new FileOutputStream("C:\\Users\\acer\\Desktop\\Report" + Faculty[i] +    ".pdf" )  );
    reportpdf.open();

    DateFormat dateFormat = new SimpleDateFormat(" dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    System.out.println(dateFormat.format(date));
    reportpdf.add(new Paragraph(" Date and time generated:") );
    reportpdf.add(new Phrase(dateFormat.format(date)  ) );
    reportpdf.add(new Paragraph("\n Report on the symptom of patient based on faculty:\n\n"  ) );

        try {
                 PdfPTable reportpdftable = new PdfPTable(3);
                 PdfPCell reportpdf_cell;
                 Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
                 Font font1 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
                 reportpdf_cell = new PdfPCell(new Phrase(" Faculty:" + Faculty[i] ,font));
                 reportpdf_cell.setColspan(3);
                 reportpdf_cell.setBackgroundColor(new BaseColor(0,121,182));
                 reportpdftable.addCell(reportpdf_cell);
                 System.out.println("Faculty : " +Faculty[i]);
                 System.out.println("==============\n");
                 System.out.println("----------------------------------------------------------------------------------");
                 System.out.println("||SIGNS CODE||\b\b||DESCRIPTION||\b\b||TOTAL PATIENT||");
                 System.out.println("----------------------------------------------------------------------------------");
                 String Signs_code = ("SIGNS CODE");
                 reportpdf_cell=new PdfPCell(new Phrase(Signs_code,font1));
                 reportpdf_cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 reportpdftable.addCell(reportpdf_cell);
                 String description = ("DESCRIPTION");
                 reportpdf_cell=new PdfPCell(new Phrase(description,font1));
                 reportpdf_cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 reportpdftable.addCell(reportpdf_cell);
                 String total_patient = ("TOTAL PATIENT");
                 reportpdf_cell=new PdfPCell(new Phrase(total_patient,font1));
                 reportpdf_cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 reportpdftable.addCell(reportpdf_cell);
                 myStmt  = myconn.prepareStatement ("select lhr_signs.Signs_code,Signs_desc,count(*) as Bilangan_pelajar from lhr_signs where LOCATION_CODE=? group by Signs_desc ORDER BY `Signs_desc` ASC ") ;
                 myStmt.setString (1,Faculty[i]);
                 myRs = myStmt.executeQuery();
            while (myRs.next()) {
                String signs_code = myRs.getString("Signs_code");
                reportpdf_cell = new PdfPCell (new Phrase (signs_code));
                reportpdftable.addCell(reportpdf_cell);
                String Signs_Description = myRs.getString("Signs_desc");
                reportpdf_cell = new PdfPCell(new Phrase(Signs_Description));
                reportpdftable.addCell(reportpdf_cell);
                String Bilangan_pelajar = myRs.getString("Bilangan_pelajar");
                reportpdf_cell = new PdfPCell (new Phrase ( Bilangan_pelajar ));
                reportpdftable.addCell(reportpdf_cell);
                System.out.println("||"+ signs_code +"||\b\b||"+ Signs_Description +"||\b\b||"+ Bilangan_pelajar +"||");
                System.out.println("----------------------------------------------------------------------------------");
            }
            reportpdf.add(reportpdftable);

         try {
            myStmt = myconn.prepareStatement ("select count(*)  Signs_desc from lhr_signs where location_code=? group by Location_code") ;
            myStmt.setString (1,Faculty[i]);
            myRs = myStmt.executeQuery();
            while(myRs.next())
            {
                String Total = myRs.getString("Signs_desc");
                System.out.println("\n\n Total patient from "+Faculty[i]+" = "+ Total +"\n\n");
                reportpdf.add(new Paragraph("\n\n Total patient from  "+ Faculty[i] +" = "+ Total + " ") );
            }
         }
         catch (Exception e)
         {
             System.err.println("Got an exception!  ");
             System.err.println(e.getMessage());
         }
        }
         catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
         }
         reportpdf.close();
         if (Desktop.isDesktopSupported())
         {
                try {
                    File myFile = new File("C:\\Users\\acer\\Desktop\\Report" + Faculty[i] + ".pdf");
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    // no application registered for PDFs
                }
             }
            }
            
}
}
}  
       catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage()); 
    }     
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[])  throws SQLException, DocumentException, FileNotFoundException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Select_by_faculty().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables

}
