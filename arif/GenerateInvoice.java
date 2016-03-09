/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
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
import com.itextpdf.text.pdf.fonts.otf.TableHeader;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Desktop;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.RMIConnector;




/**
 *
 * @author acer
 */
public class GenerateInvoice extends javax.swing.JFrame {

    /**
     * Creates new form GenerateInvoice
     */
    public GenerateInvoice() {
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
                       reportpdf.left() + 250,  reportpdf.bottom() - 20, 0);
            //ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(String.format("Page %d of", writer.getPageNumber())), 
                  // reportpdf.right() - 12 , reportpdf.bottom() - 20, 0);
//             ColumnText.showTextAligned(total, Element.ALIGN_RIGHT, new Phrase(String.valueOf(writer.getPageSize())), 
//                     reportpdf.right() - 2 , reportpdf.bottom() - 20, 0);
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

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Generate Invoice");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setText("Generate Invoice");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        try
        {
        Document Invoicepdf = new Document(PageSize.A4);
        TableHeader event = new TableHeader();
        String timeStamp1 = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()); 
        String timeStamp2 = new SimpleDateFormat("MMMM yyyy").format(Calendar.getInstance().getTime()); 
        String date = timeStamp2;
        PdfWriter writer = PdfWriter.getInstance (Invoicepdf, new FileOutputStream( "Invoice  .pdf")  );
        writer .setPageEvent(event);
        Invoicepdf.open(); 
        PdfPTable header = new PdfPTable(3);
        header.setWidths(new int[]{6,1,10});
             //float[] columnWidths = {2f, 1.19f};
       // header.setWidths(columnWidths);
             Image logo = Image.getInstance("LogoUTeM.png" );
             logo.scalePercent(8);
             PdfPCell cell1 = new PdfPCell(logo);
             // logo.setAlignment(Element.ALIGN_CENTER);
              cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
              cell1.setColspan(3);
             cell1.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell1);
        Font teks = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
        PdfPCell cell3 = new PdfPCell(new Paragraph("\n\nBAHAGIAN PENGURUSAN ORGANISASI \n UNIVERSITI TEKNIKAL MALAYSIA MELAKA\nHANG TUAH JAYA \n76100 DURIAN TUNGGAL \nMELAKA, MALAYSIA   ", teks));
             cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell3.setColspan(3);
             cell3.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell3);
             PdfPCell cell4 = new PdfPCell(new Paragraph("\n\nMAKLUMAT KLINIK ", teks));
             cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell4.setColspan(3);
             cell4.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell4); 
             PdfPCell cell5 = new PdfPCell(new Paragraph("NAMA KLINIK", teks));
             cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell5.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell5); 
             PdfPCell cell7 = new PdfPCell(new Paragraph(":", teks));
             cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell7.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell7); 
        PdfPCell cell6 = new PdfPCell(new Paragraph("K60-KLINIK PELAJAR UTEM", teks));
             cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell6.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell6); 
             PdfPCell cell8 = new PdfPCell(new Paragraph("ALAMAT", teks));
             cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell8.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell8); 
             PdfPCell cell10 = new PdfPCell(new Paragraph(":", teks));
             cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell10.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell10); 
        PdfPCell cell9 = new PdfPCell(new Paragraph("UNIVERSITI TEKNIKAL MALAYSIA MELAKA, HANG TUAH JAYA, DURIAN TUNGGAL,76100 DURIAN TUNGGAL MELAKA", teks));
             cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell9.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell9); 
            PdfPCell cell11 = new PdfPCell(new Paragraph("NAMA BANK", teks));
             cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell11.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell11); 
             PdfPCell cell12 = new PdfPCell(new Paragraph(":", teks));
             cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell12.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell12); 
        PdfPCell cell13 = new PdfPCell(new Paragraph("BANK ISLAM MALAYSIA BERHAD", teks));
             cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell13.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell13); 
             PdfPCell cell14 = new PdfPCell(new Paragraph("NO. AKAUN BANK", teks));
             cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell14.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell14); 
             PdfPCell cell15 = new PdfPCell(new Paragraph(":", teks));
             cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell15.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell15); 
        PdfPCell cell16 = new PdfPCell(new Paragraph("0", teks));
             cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell16.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell16); 
             PdfPCell cell17 = new PdfPCell(new Paragraph("NO. INVOIS", teks));
             cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell17.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell17); 
             PdfPCell cell18 = new PdfPCell(new Paragraph(":", teks));
             cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell18.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell18); 
        PdfPCell cell19 = new PdfPCell(new Paragraph("1/2015", teks));
             cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell19.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell19); 
              PdfPCell cell20 = new PdfPCell(new Paragraph("TARIKH INVOIS", teks));
             cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell20.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell20); 
             PdfPCell cell21 = new PdfPCell(new Paragraph(":", teks));
             cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell21.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell21); 
        PdfPCell cell22 = new PdfPCell(new Paragraph( timeStamp1 , teks));
             cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell22.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell22); 
             PdfPCell cell23 = new PdfPCell(new Paragraph("TUNTUTAN INVOIS BAGI BULAN", teks));
             cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell23.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell23); 
             PdfPCell cell24 = new PdfPCell(new Paragraph(":", teks));
             cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell24.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell24); 
        PdfPCell cell25 = new PdfPCell(new Paragraph( date, teks));
             cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell25.setBorder(Rectangle.NO_BORDER);
             header.addCell(cell25); 
             PdfPCell cell33 = new PdfPCell(new Paragraph("\n", teks));
             cell33.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell33.setBorder(Rectangle.NO_BORDER);
             cell33.setColspan(3);
             header.addCell(cell33); 
             Invoicepdf.add(header);
             PdfPTable content = new PdfPTable(7);
            content.setWidths(new int[]{2,3,3,5,5,4,5});
            Font font1 = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
            String No = ("NO.");
            String TARIKH_RAWATAN = ("Tarikh Rawatan");
            String No_Staf = ("No.Staf");
            String Nama_Staf=("Nama Staf");
            String Nama_Pesakit=("Nama Pesakit");
            String Jenis_Rawatan=("Jenis Rawatan");
            String Kos_Rawatan=("Kos Rawatan(RM)");
                 PdfPCell cell26 = new PdfPCell(new Phrase(No,font1));
                 cell26.setBackgroundColor( BaseColor.WHITE);
                 content.addCell(cell26);
                 PdfPCell cell27 = new PdfPCell(new Phrase(TARIKH_RAWATAN,font1));
                 cell27.setBackgroundColor(BaseColor.WHITE);
                 content.addCell(cell27);
                 PdfPCell cell28 = new PdfPCell(new Phrase(No_Staf,font1));
                 cell28.setBackgroundColor(BaseColor.WHITE);
                 content.addCell(cell28);
                 PdfPCell cell29 = new PdfPCell(new Phrase(Nama_Staf,font1));
                 cell29.setBackgroundColor(BaseColor.WHITE);
                 content.addCell(cell29);
             PdfPCell cell30 = new PdfPCell(new Phrase(Nama_Pesakit,font1));
                 cell30.setBackgroundColor(BaseColor.WHITE);
                 content.addCell(cell30);
                 PdfPCell cell31 = new PdfPCell(new Phrase(Jenis_Rawatan,font1));
                 cell31.setBackgroundColor(BaseColor.WHITE);
                 content.addCell(cell31);
             PdfPCell cell32 = new PdfPCell(new Phrase(Kos_Rawatan,font1));
                 cell32.setBackgroundColor(BaseColor.WHITE);
                 content.addCell(cell32);
             
             Invoicepdf.add(content);
         Invoicepdf.newPage();         
 Invoicepdf.close();
        }
        
       
        
        
        catch ( DocumentException e)
         {
             System.err.println("Got an exception!  ");
             System.err.println(e.getMessage());
         } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerateInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerateInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GenerateInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateInvoice().setVisible(true);
            }
        });
    }

    
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
