package select_by_faculty;

import com.itextpdf.text.DocumentException;
import java.sql.*;
import java.util.ArrayList.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import java.io.*;

public class Select_by_faculty
{
    public static void main(String[]args) throws SQLException, DocumentException, FileNotFoundException
    {
        String connectionURL = "jdbc:mysql://127.0.0.1/dump?user=root&password=";
        Connection myconn = DriverManager.getConnection(connectionURL);
        Document reportpdf = new Document();
        PdfWriter.getInstance (reportpdf, new FileOutputStream("C:\\Users\\acer\\Desktop\\Report.pdf")  );
        reportpdf.open();
        reportpdf.add(new Paragraph("\b\b Report on the symptom of patient based on faculty:\n\n") );
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
for(int i=0; i<Faculty.length; i++)
{
    {
        try {
                 PdfPTable reportpdftable = new PdfPTable(3);
                 PdfPCell reportpdf_cell;
                 Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
                 Font font1 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
                 reportpdf_cell = new PdfPCell(new Phrase("Faculty:" + Faculty[i] ,font));
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
                System.out.println("Total patient from "+Faculty[i]+" = "+ Total +"\n\n");
                reportpdf.add(new Paragraph("\n Total patient from  "+ Faculty[i] +" = "+ Total + " \n\n\n") );
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
            }
}
}            
}
       reportpdf.close();
    }
}






