package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.StudentDAOImpl;
import model.Student;

@WebServlet("/DownloadIdCardServlet")

public class DownloadIdCardServlet
extends HttpServlet {

    private static final long
    serialVersionUID = 1L;

    protected void doGet(

            HttpServletRequest request,

            HttpServletResponse response)

            throws ServletException,
            IOException {

        int id = Integer.parseInt(
                request.getParameter("id"));

        StudentDAOImpl dao =
                new StudentDAOImpl();

        Student s =
                dao.getStudentById(id);

        response.setContentType(
                "application/pdf");

        response.setHeader(

        "Content-Disposition",

        "attachment; filename=id_card.pdf");

        try {

            Document document =
                    new Document();

            PdfWriter.getInstance(

                    document,

                    response.getOutputStream());

            document.open();

            // TITLE

            Font titleFont =

            FontFactory.getFont(

                    FontFactory.HELVETICA_BOLD,

                    20);

            Paragraph title =

            new Paragraph(

            "Student ID Card",

            titleFont);

            title.setSpacingAfter(20);

            document.add(title);

            // PHOTO

            String imagePath =

            getServletContext()

            .getRealPath("/uploads/")
            + s.getPhoto();

            try {

                Image photo =
                        Image.getInstance(
                                imagePath);

                photo.scaleToFit(
                        120,
                        120);

                document.add(photo);

            } catch(Exception e) {

                e.printStackTrace();
            }

            // DETAILS TABLE

            PdfPTable table =
                    new PdfPTable(2);

            table.setSpacingBefore(20);

            table.setWidthPercentage(100);

            table.addCell("Student ID");
            table.addCell(String.valueOf(
                    s.getId()));

            table.addCell("Name");
            table.addCell(s.getName());

            table.addCell("Course");
            table.addCell(s.getCourse());

//            table.addCell("Marks");
//            table.addCell(String.valueOf(
//                    s.getMarks()));

            document.add(table);

            // QR CONTENT

            String qrData =

            "Student ID: " + s.getId()

            + "\nName: " + s.getName()

            + "\nCourse: " + s.getCourse()

            //+ "\nMarks: " + s.getMarks()
            
            + "\nStudent Management System";

            // GENERATE QR

            BitMatrix matrix =

            new MultiFormatWriter()

            .encode(

                    qrData,

                    BarcodeFormat.QR_CODE,

                    200,

                    200);

            BufferedImage qrImage =

            MatrixToImageWriter
            .toBufferedImage(matrix);

            ByteArrayOutputStream baos =
                    new ByteArrayOutputStream();

            ImageIO.write(
                    qrImage,
                    "png",
                    baos);

            Image qr =

            Image.getInstance(
                    baos.toByteArray());

            qr.setSpacingBefore(20);

            qr.scaleToFit(
                    150,
                    150);

            document.add(qr);

            document.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}