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
import static jdk.nashorn.internal.objects.NativeString.substring;
import main.RMIConnector;

/**
 *
 * @author Amalina
 */
public class after_modify extends javax.swing.JFrame {

    // call library
    RMIConnector rc = new RMIConnector();

    // declaration host and port
    String host = "biocore-devp.utem.edu.my";
    int port = 1099; // for now, stick to this port

    String Table_click1 = billing.setValue();
    String id_no = generate.setValueCustId();
    String bill_no = generate.setValueBillNo();
    String cust_id = id_no;
    static String BILL_NO;
    static String getItemCode;
    
        //String ;
    static String Table_click2;
    static String Table_click3;
    static String orderno;

    public static String setValue1() {
        return Table_click2;
    }

    public static String setValue() {
        return Table_click3;
    }

    public static String setValue2() {
        return orderno;
    }

    public static String setValueBillNo() {
        return BILL_NO;
    }
  
    public static String setValueitemCode() {
        return getItemCode;
    }
    /**
     * Creates new form generate
     */
    public after_modify() {
        initComponents();
        billdetails();

        //  System.out.println(Table_click1); 
    }
    // static String Table_click1;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jtf_name = new javax.swing.JTextField();
        jtf_address = new javax.swing.JTextField();
        jtf_ic = new javax.swing.JTextField();
        jtf_id = new javax.swing.JTextField();
        jtf_telnum = new javax.swing.JTextField();
        jtf_billNo = new javax.swing.JTextField();
        jtf_date = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_print = new javax.swing.JButton();
        btn_confirm = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Bill Details"));
        jPanel1.setToolTipText("");

        jtf_name.setEditable(false);

        jtf_address.setEditable(false);

        jtf_ic.setEditable(false);

        jtf_id.setEditable(false);

        jtf_telnum.setEditable(false);

        jtf_billNo.setEditable(false);
        jtf_billNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_billNoActionPerformed(evt);
            }
        });

        jtf_date.setEditable(false);
        jtf_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_dateActionPerformed(evt);
            }
        });

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
                "Item", "Description", "Quantity", "Unit Price", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btn_print.setText("Print Receipt");
        btn_print.setEnabled(false);
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });

        btn_confirm.setText("Confirm");
        btn_confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_confirmActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.setEnabled(false);
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        btn_add.setText("Add Item");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
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
                                    .addComponent(jtf_ic)
                                    .addComponent(jtf_id)
                                    .addComponent(jtf_telnum))
                                .addGap(77, 77, 77)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtf_billNo, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jtf_date)))
                            .addComponent(jtf_name)
                            .addComponent(jtf_address)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtf_name, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtf_address, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jtf_ic))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_billNo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_telnum, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_date, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_print, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        pdf();
        try { //keluarkan pdf after click
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Users\\user\\Dropbox\\eKlinik\\Receipt.pdf");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }      
billing m = new billing(); //set new window
        m.setVisible(true);//set new window visible
        dispose(); // for hide current window
    }//GEN-LAST:event_btn_printActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // TODO add your handling code here:
        billing m = new billing(); //set new window
        m.setVisible(true);//set new window visible
        dispose(); // for hide current window
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_confirmActionPerformed
        // TODO add your handling code here:
        btn_print.setEnabled(true);
        btn_confirm.setEnabled(false);
        btn_add.setEnabled(false);
        btn_cancel.setEnabled(false);
    }//GEN-LAST:event_btn_confirmActionPerformed

    private void jtf_billNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_billNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_billNoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        getItemCode = (jTable1.getModel().getValueAt(row, 0).toString());
        edit m = new edit(); //set new window
        m.setVisible(true);//set new window visible
        dispose(); // for hide current window
        System.out.println("Table_click2" + Table_click2);

    }//GEN-LAST:event_jTable1MouseClicked

    private void jtf_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_dateActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        addItem m = new addItem(); //set new window
        m.setVisible(true);//set new window visible
        dispose(); // for hide current window
    }//GEN-LAST:event_btn_addActionPerformed

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
                + "WHERE ch.customer_id = '"+cust_id+"'";
                ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);// execute query

                jtf_name.setText(data.get(0).get(0));
                jtf_address.setText(data.get(0).get(1));
                jtf_ic.setText(data.get(0).get(2));
                jtf_id.setText(data.get(0).get(3));
                jtf_telnum.setText(data.get(0).get(4));
                jtf_billNo.setText(data.get(0).get(5));
                jtf_date.setText(data.get(0).get(6));
                DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();

                //remove all row
                int rowCount1 = model1.getRowCount();
                for (int i = rowCount1 - 1; i >= 0; i--) {
                    model1.removeRow(i);
//                System.out.println("i " + i);
                }

                //add row and show value
                for (int i = 0; i < data.size(); i++) {
                    model1.addRow(new Object[]{"", "", "", "", ""});

                    //jTable1.setValueAt((Object)i, 1, 1);
                    jTable1.setValueAt(data.get(i).get(7), i, 0);
                    jTable1.setValueAt(data.get(i).get(8), i, 1);
                    jTable1.setValueAt(data.get(i).get(9), i, 2);
                    jTable1.setValueAt(data.get(i).get(10), i, 3);
                    jTable1.setValueAt(data.get(i).get(11), i, 4);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void pdf() {
        BILL_NO = bill_no;
        Document document = new Document(PageSize.A4, 36, 36, 64, 36); //create new document
        document.setMargins(40, 30, 50, 50); //set document margin

        int num = 1;
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Receipt.pdf"));
            document.open(); //open document
            //database query

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
                    + "(cd.quantity * cd.item_amt), "
                    + "ch.item_amt "
                    + "FROM customer_hdr ch "
                    + "INNER JOIN customer_dtl cd "
                    + "ON ch.customer_id = cd.customer_id "
                    + "INNER JOIN pms_patient_biodata pb "
                    + "ON cd.customer_id = pb.ID_NO "
                    + "WHERE ch.customer_id = '" + cust_id + "'  and ch.bill_no = '" + BILL_NO + "' ";
            //+ "AND ch.txn_date = '2016-02-01'";
            ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);// execute query

            //initialize pdf
            Font recno = new Font(Font.TIMES_ROMAN, 10);
            Font recti = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font rectem = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font rectemja = new Font(Font.COURIER, 12);
            Font rectemjabold = new Font(Font.COURIER, 12, Font.BOLD);
            //--------------------------table bill------------------------------------------>
            PdfPTable table = new PdfPTable(6);
            table.setWidths(new float[]{0.5f, 1.5f, 4f, 1.5f, 1.5f, 1.5f});
            table.setLockedWidth(true);
            table.setTotalWidth(document.right() - document.left());
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

            String nama = data.get(0).get(0);
            String address = data.get(0).get(1);
            String ic = data.get(0).get(2);
            String id = data.get(0).get(3);
            String phone = data.get(0).get(4);
            String bill_no = data.get(0).get(5);
            String date = data.get(0).get(6);

            // String grandtotal = data.get(i).get(10);
            //System.out.println(name);
            //--------------------------Receipt item------------------------------------------>
            PdfPCell cell11 = new PdfPCell(new Phrase("Name", rectem));
            cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell11.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell12 = new PdfPCell(new Phrase(": " + nama, rectemja));
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
            PdfPCell cell44 = new PdfPCell(new Phrase(": " + bill_no, rectemja));
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

            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("dd-MM-yyyy"); //2015-01-06 
            Date date1 = new Date();
            String getdate = dateFormat.format(date1);

            PdfPCell cell53 = new PdfPCell(new Phrase("Date", rectem));
            cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell53.setBorder(Rectangle.NO_BORDER);

            PdfPCell cell54 = new PdfPCell(new Phrase(": " + getdate, rectemja));
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
            cell66.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(cell61);
            table.addCell(cell62);
            table.addCell(cell63);
            table.addCell(cell64);
            table.addCell(cell65);
            table.addCell(cell66);

            for (int i = 0; i < data.size(); i++) {

                String NO = Integer.toString(num);

                String item = data.get(i).get(7);
                String description = data.get(i).get(8);
                String quantity = data.get(i).get(9);
                String price = data.get(i).get(10);
                String total = data.get(i).get(11);
                // String grandtotal = data.get(i).get(10);
                //System.out.println(name);

                PdfPCell cell71 = new PdfPCell(new Phrase(NO, rectemja));
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

                num = num + 1;
            }
            //--------------------------table bill------------------------------------------>
            PdfPTable total = new PdfPTable(6);
            total.setWidths(new float[]{0.5f, 1.5f, 4f, 1.5f, 1.5f, 1.5f});
            total.setLockedWidth(true);
            total.setTotalWidth(document.right() - document.left());
            //--------------------------table bill------------------------------------------>

            String gt2 = data.get(0).get(12);
//            System.out.println(gt);
            PdfPCell cell81 = new PdfPCell(new Phrase("Grand Total : RM  ", rectem));
            cell81.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell81.setColspan(5);
            PdfPCell cell86 = new PdfPCell(new Phrase(gt2, rectemjabold));
            cell86.setHorizontalAlignment(Element.ALIGN_CENTER);

            total.addCell(cell81);
            total.addCell(cell86);
            //-------------------------------------------------------------------->

            document.add(header_table);
            document.add(table);
            document.add(total);
            document.close();//close document

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_confirm;
    private javax.swing.JButton btn_print;
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
    private javax.swing.JTextField jtf_address;
    private javax.swing.JTextField jtf_billNo;
    private javax.swing.JTextField jtf_date;
    private javax.swing.JTextField jtf_ic;
    private javax.swing.JTextField jtf_id;
    private javax.swing.JTextField jtf_name;
    private javax.swing.JTextField jtf_telnum;
    // End of variables declaration//GEN-END:variables
}
