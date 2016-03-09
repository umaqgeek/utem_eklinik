/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eklinik_bill;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.RMIConnector;
import java.util.Scanner;
import eklinik_bill.billing;

/**
 *
 * @author Amalina
 */
public class generate extends javax.swing.JFrame {

    // call library
    RMIConnector rc = new RMIConnector();

    // declaration host and port
    String host = "biocore-devp.utem.edu.my";
    int port = 1099; // for now, stick to this port

    /**
     * Creates new form generate
     */
    public generate() {
        initComponents();
        billdetails();
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
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Bill Details"));
        jPanel1.setToolTipText("");

        jLabel1.setText("Name");

        jLabel2.setText("Address");

        jLabel3.setText("IC Number");

        jLabel4.setText("Other ID");

        jLabel5.setText("Phone No.");

        jLabel6.setText("Bill No.");

        jLabel7.setText("Date");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Item", "Description", "Quantity", "Unit Price", "Total Price", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Print Receipt");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Confirm");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Add Item");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Modify");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Cancel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField5))
                                .addGap(77, 77, 77)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextField7)))
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        pdf();
        try { //keluarkan pdf after click
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Users\\user\\Dropbox\\eKlinik\\Receipt.pdf");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        jButton4.setEnabled(false);
        jTable1.setEditingRow(jTable1.getEditingRow());


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        billing m = new billing(); //set new window
        m.setVisible(true);//set new window visible
        dispose(); // for hide current window
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        // add row
        addRow();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(generate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(generate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(generate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(generate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new generate().setVisible(true);
            }
        });
    }

    public void billdetails() {
        try {
//        int row = jTable4.getSelectedRow(); //get no of row
//            String Table_click = (jTable4.getModel().getValueAt(row, 0).toString()); //assign row value to select
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("MMyyyy"); //2015-01-06 
            Date date = new Date();
            String getdate = dateFormat.format(date);
            String sqlseqno = "SELECT MAX(last_seq_no) AS no FROM last_seq_no";
            ArrayList<ArrayList<String>> dataseq = rc.getQuerySQL(host, port, sqlseqno);// execute query
            String test_seq = dataseq.get(0).get(0);
            int ts = Integer.parseInt(test_seq);
            int tss = ts + 1;
            String BILL_NO = tss + getdate;
            int noseq = 1;
            int NO = noseq;
            String sql = "SELECT DISTINCT "
                    + "pb.PATIENT_NAME,  pb.HOME_ADDRESS, pb.NEW_IC_NO, pb.ID_NO, pb.MOBILE_PHONE, NOW(), "
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
            ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);// execute query

            // System.out.println(Table_click);
            //DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            jTextField1.setText(data.get(0).get(0));
            jTextField2.setText(data.get(0).get(1));
            jTextField3.setText(data.get(0).get(2));
            jTextField4.setText(data.get(0).get(3));
            jTextField5.setText(data.get(0).get(4));
            jTextField6.setText(BILL_NO);
            jTextField7.setText(data.get(0).get(5));
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            //remove all row
            int rowCount = model.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
                System.out.println("i " + i);
            }

            //add row and show value
            for (int i = 0; i < data.size(); i++) {
                model.addRow(new Object[]{NO, "", "", "", "", "", ""});

                //jTable1.setValueAt((Object)i, 1, 1);
                jTable1.setValueAt(data.get(i).get(6), i, 1);
                jTable1.setValueAt(data.get(i).get(7), i, 2);
                jTable1.setValueAt(data.get(i).get(8), i, 3);
                jTable1.setValueAt(data.get(i).get(9), i, 4);
                jTable1.setValueAt(data.get(i).get(10), i, 5);
                jTable1.setValueAt(data.get(i).get(6), i, 6);
                NO++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addRow() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            //add row and show value
            model.addRow(new Object[]{"", "", "", "", "", "", ""});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pdf() {
        Document document = new Document(PageSize.A4, 36, 36, 64, 36); //create new document
        document.setMargins(40, 30, 50, 50); //set document margin
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Receipt.pdf"));
            document.open(); //open document
            //database query
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("MMyyyy"); //2015-01-06 
            Date date = new Date();
            String getdate = dateFormat.format(date);
            String sqlseqno = "SELECT MAX(last_seq_no) AS no FROM last_seq_no";
            ArrayList<ArrayList<String>> dataseq = rc.getQuerySQL(host, port, sqlseqno);// execute query
            String test_seq = dataseq.get(0).get(0);
            int ts = Integer.parseInt(test_seq);
            int tss = ts + 1;
            String BILL_NO = tss + getdate;
            int noseq = 1;
            int NO = noseq;
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
            ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);// execute query

            for (int i = 0; i < data.size(); i++) {
                String name = data.get(i).get(0);
                String address = data.get(i).get(1);
                String ic = data.get(i).get(2);
                String id = data.get(i).get(3);
                String phone = data.get(i).get(4);
                String item = data.get(i).get(5);
                String description = data.get(i).get(6);
                String quantity = data.get(i).get(7);
                String price = data.get(i).get(8);
                String total = data.get(i).get(9);
                // String grandtotal = data.get(i).get(10);
                //System.out.println(name);

                //initialize pdf
                Font recno = new Font(Font.TIMES_ROMAN, 10);
                Font recti = new Font(Font.HELVETICA, 16, Font.BOLD);
                Font rectem = new Font(Font.HELVETICA, 12, Font.BOLD);
                Font rectemja = new Font(Font.COURIER, 12);
                Font rectemjabold = new Font(Font.COURIER, 12, Font.BOLD);
                //--------------------------receipt no------------------------------------------>
                PdfPTable header_table = new PdfPTable(4);
                header_table.setWidths(new float[]{2f, 7f, 1.5f, 4f});
                header_table.setLockedWidth(true);
                header_table.setTotalWidth(document.right() - document.left());

                //--------------------------table header------------------------------------------>
                Image logo = Image.getInstance("pic/logo.png");
                logo.scaleAbsolute(115, 50);

                PdfPCell cell1 = new PdfPCell(logo);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setBorder(Rectangle.NO_BORDER);
                cell1.setColspan(4);
                cell1.setLeading(15f, 0.3f);
                header_table.addCell(cell1);
                //-----------------------------Title--------------------------------------->
                PdfPCell cell200 = new PdfPCell(new Phrase(" ", recti));
                cell200.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell200.setBorder(Rectangle.NO_BORDER);
                cell200.setColspan(4);
                header_table.addCell(cell200);
                PdfPCell cell2 = new PdfPCell(new Phrase("RECEIPT", recti));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setBorder(Rectangle.NO_BORDER);
                cell2.setColspan(4);
                header_table.addCell(cell2);

                PdfPCell cell001 = new PdfPCell(new Phrase(" ", recti));
                cell001.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell001.setBorder(Rectangle.NO_BORDER);
                cell001.setColspan(4);
                header_table.addCell(cell001);
                //--------------------------Receipt item------------------------------------------>
                PdfPCell cell11 = new PdfPCell(new Phrase("Name", rectem));
                cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell11.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell12 = new PdfPCell(new Phrase(": " + name, rectemja));
                cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell12.setBorder(Rectangle.NO_BORDER);
                cell12.setColspan(3);

                header_table.addCell(cell11);
                header_table.addCell(cell12);

                PdfPCell cell21 = new PdfPCell(new Phrase("Address", rectem));
                cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell21.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell22 = new PdfPCell(new Phrase(": " + address, rectemja));
                cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell22.setBorder(Rectangle.NO_BORDER);
                cell22.setColspan(3);

                header_table.addCell(cell21);
                header_table.addCell(cell22);

                PdfPCell cell31 = new PdfPCell(new Phrase("IC Number", rectem));
                cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell31.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell32 = new PdfPCell(new Phrase(": " + ic, rectemja));
                cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell32.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell33 = new PdfPCell(new Phrase(" ", rectem));
                cell33.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell33.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell34 = new PdfPCell(new Phrase(" ", rectemja));
                cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell34.setBorder(Rectangle.NO_BORDER);

                header_table.addCell(cell31);
                header_table.addCell(cell32);
                header_table.addCell(cell33);
                header_table.addCell(cell34);

                PdfPCell cell41 = new PdfPCell(new Phrase("Other ID", rectem));
                cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell41.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell42 = new PdfPCell(new Phrase(": " + id, rectemja));
                cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell42.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell43 = new PdfPCell(new Phrase("Bill No", rectem));
                cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell43.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell44 = new PdfPCell(new Phrase(": " + BILL_NO, rectemja));
                cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell44.setBorder(Rectangle.NO_BORDER);

                header_table.addCell(cell41);
                header_table.addCell(cell42);
                header_table.addCell(cell43);
                header_table.addCell(cell44);

                PdfPCell cell51 = new PdfPCell(new Phrase("No. Tel", rectem));
                cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell51.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell52 = new PdfPCell(new Phrase(": " + phone, rectemja));
                cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell52.setBorder(Rectangle.NO_BORDER);
                PdfPCell cell53 = new PdfPCell(new Phrase("Date", rectem));
                cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell53.setBorder(Rectangle.NO_BORDER);
                DateFormat dateFormat1;
                dateFormat1 = new SimpleDateFormat("dd/MM/yyyy"); //2015-01-06 
                Date date1 = new Date();
                String getdate1 = dateFormat1.format(date1);
                PdfPCell cell54 = new PdfPCell(new Phrase(": " + getdate1, rectemja));
                cell54.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell54.setBorder(Rectangle.NO_BORDER);

                header_table.addCell(cell51);
                header_table.addCell(cell52);
                header_table.addCell(cell53);
                header_table.addCell(cell54);

                PdfPCell cell002 = new PdfPCell(new Phrase(" ", recti));
                cell002.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell002.setBorder(Rectangle.NO_BORDER);
                cell002.setColspan(4);
                header_table.addCell(cell002);
                //-------------------------------------------------------------------->
                PdfPTable table = new PdfPTable(6);
                table.setWidths(new float[]{0.5f, 1.5f, 4f, 1.5f, 1.5f, 1.5f});
                table.setLockedWidth(true);
                table.setTotalWidth(document.right() - document.left());

                PdfPCell cell61 = new PdfPCell(new Phrase("No.", rectem));
                cell61.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell cell62 = new PdfPCell(new Phrase("Item", rectem));
                cell62.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell cell63 = new PdfPCell(new Phrase("Description", rectem));
                cell63.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell cell64 = new PdfPCell(new Phrase("Quantity", rectem));
                cell64.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell cell65 = new PdfPCell(new Phrase("Unit Price", rectem));
                cell65.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell cell66 = new PdfPCell(new Phrase("Total Price", rectem));
                cell66.setHorizontalAlignment(Element.ALIGN_LEFT);

                table.addCell(cell61);
                table.addCell(cell62);
                table.addCell(cell63);
                table.addCell(cell64);
                table.addCell(cell65);
                table.addCell(cell66);

                PdfPCell cell71 = new PdfPCell(new Phrase("1.", rectemja));
                cell71.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell cell72 = new PdfPCell(new Phrase(item, rectemja));
                cell72.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell cell73 = new PdfPCell(new Phrase(description, rectemja));
                cell73.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell cell74 = new PdfPCell(new Phrase(quantity, rectemja));
                cell74.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell cell75 = new PdfPCell(new Phrase(price, rectemja));
                cell75.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell cell76 = new PdfPCell(new Phrase(total, rectemja));
                cell76.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell71);
                table.addCell(cell72);
                table.addCell(cell73);
                table.addCell(cell74);
                table.addCell(cell75);
                table.addCell(cell76);

                int grandtotal = 0;
                int total1 = Integer.parseInt(total);
                grandtotal = grandtotal + total1;
                String gt = Integer.toString(grandtotal);
                //System.out.println(grandtotal);

                PdfPCell cell81 = new PdfPCell(new Phrase("Grand Total : RM  ", rectem));
                cell81.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell81.setColspan(5);
                PdfPCell cell86 = new PdfPCell(new Phrase(gt, rectemjabold));
                cell86.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell81);
                table.addCell(cell86);
                //-------------------------------------------------------------------->

                document.add(header_table);
                document.add(table);
                document.close();//close document
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}