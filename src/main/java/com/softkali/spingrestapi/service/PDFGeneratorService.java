package com.softkali.spingrestapi.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response) throws IOException, WriterException {

        String track_no="dhffh";

        String logo = "image\\logo.jpg";


        BitMatrix bitMatrix=new MultiFormatWriter().encode(track_no, BarcodeFormat.PDF_417,100,40);

        BufferedImage bufferedImage= MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] bytes = baos.toByteArray();




        PdfWriter writer=new PdfWriter(response.getOutputStream());
            PdfDocument pdfDocument=new PdfDocument(writer);
            Document document=new Document(pdfDocument);

            pdfDocument.setDefaultPageSize(PageSize.A4);
            document.setMargins(0,0,0,0);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            ImageData imageData = ImageDataFactory.create(bytes);
            Image image=new Image(imageData).setHorizontalAlignment(HorizontalAlignment.CENTER);

            Paragraph tracking_number=new Paragraph("Track_no: 1243434656").setBold().setFontSize(15).setTextAlignment(TextAlignment.CENTER);

            float[] width ={100f,100f,100f,100f,100f};
            Table table=new Table(width);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);

//                table.addCell(new Cell(1,5).add(image));
            table.addCell(new Cell(1,5).add(image));
            table.addCell(new Cell(1,5).add(tracking_number));


            float[] width2 ={100f,100f,100f,100f,100f,100f,100f,100f,100f,100f};
            Table table2=new Table(width2);

            table2.setHorizontalAlignment(HorizontalAlignment.CENTER);

            Paragraph Referancekey=new Paragraph("King World Wide Courier");
            Paragraph Referance=new Paragraph("King World Wide Courier").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);


            table2.addCell(new Cell(1,1).add(Referancekey));
            table2.addCell(new Cell(1,9).add(Referance));


            float[] widthShipmentDetail ={100f,100f,100f,100f,100f,100f,100f,100f,100f,100f};
            Table tableShipmentDetail=new Table(widthShipmentDetail);
            tableShipmentDetail.setMargins(2,20,20,0);
            tableShipmentDetail.setHorizontalAlignment(HorizontalAlignment.CENTER);

            Paragraph ShipmentDetail=new Paragraph("Shipment Detail")
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph Origin=new Paragraph("BOM").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph OriginKey=new Paragraph("BOM");
            Paragraph DeastinationKey=new Paragraph("USA");
            Paragraph Deastination=new Paragraph("USA").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph ProductKey=new Paragraph("EXP/PPX");
            Paragraph Product=new Paragraph("EXP/PPX").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);

            tableShipmentDetail.addCell(new Cell(3,1).add(ShipmentDetail));
            tableShipmentDetail.addCell(new Cell(1,2).add(OriginKey).add(Origin));
            tableShipmentDetail.addCell(new Cell(1,3).add(DeastinationKey).add(Deastination));
            tableShipmentDetail.addCell(new Cell(1,4).add(ProductKey).add(Product));

            Paragraph Description_of_Goods=new Paragraph("Dairy with pen and calender");
            Paragraph Weight=new Paragraph("Weight: 24.23 kg");
            Paragraph Charagable=new Paragraph("Charagable: 6.4 kg");
            Paragraph Description=new Paragraph("Description of Goods:");
            Paragraph Prepaid=new Paragraph("Prepaid");


            tableShipmentDetail.addCell(new Cell(1,3).add(Weight).add(Charagable));
            tableShipmentDetail.addCell(new Cell(1,4).add(Description).add(Description_of_Goods));
            tableShipmentDetail.addCell(new Cell(1,2).add(Prepaid).setBold().setFontSize(15));


            Paragraph PCS=new Paragraph("1/6").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph PCSKey=new Paragraph("PCS");

            Paragraph CustomValue=new Paragraph("6 USD").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph CustomValueKey=new Paragraph("COD Value::");

            Paragraph Goodsorigin=new Paragraph("").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph GoodsoriginKey=new Paragraph("Goods origin:");

            Paragraph CODValue=new Paragraph("0 INR").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph CODValueKey=new Paragraph("COD Value:: ");

            tableShipmentDetail.addCell(new Cell(1,2).add(CustomValueKey).add(CustomValue));
            tableShipmentDetail.addCell(new Cell(1,3).add(GoodsoriginKey).add(Goodsorigin));
            tableShipmentDetail.addCell(new Cell(1,2).add(CODValueKey).add(CODValue));
            tableShipmentDetail.addCell(new Cell(1,2).add(PCSKey).add(PCS));




            Paragraph SenderDetail=new Paragraph("Shipper Detail")
                    .setTextAlignment(TextAlignment.CENTER);

            String Account="BOM456547568";
            Paragraph SenderName=new Paragraph("SJH KFBKH LJVL KHH K OV K KHI ").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph SenderAddress=new Paragraph("Mumbai").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph SenderCounty=new Paragraph("India").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            String Ret="KING";
            String SenderTel="8975353656";


        Paragraph AccountKey=new Paragraph("Account: "+Account+"   Ret:"+Ret);
        Paragraph SenderTelKwy=new Paragraph("Tel: "+SenderTel);

        tableShipmentDetail.addCell(new Cell(1,1).add(SenderDetail));

            tableShipmentDetail.addCell(new Cell(1,9).add(AccountKey)
                    .add(SenderName).add(SenderAddress).add(SenderCounty).add(SenderTelKwy));



            Paragraph ReceiverDetail=new Paragraph("Receiver Detail")
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph ReceiverName=new Paragraph("SJH KFBKH LJVL KHH K OV K KHI ").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph ReceiverAddress=new Paragraph("Mumbai").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph ReceiverCounty=new Paragraph("India").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph Remarks=new Paragraph("FOR DGLKJNMKL").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            Paragraph PickupDate=new Paragraph("11/12/2022").setBold().setFontSize(15).setTextAlignment(TextAlignment.LEFT);
            String ReceiverTel="8975353656";


            tableShipmentDetail.addCell(new Cell(1,1).add(ReceiverDetail));

            tableShipmentDetail.addCell(new Cell(1,9).add(AccountKey)
                    .add(ReceiverName).add(ReceiverAddress).add(ReceiverCounty).add(AccountKey));


            tableShipmentDetail.addCell(new Cell(1,1).add(AccountKey));
            tableShipmentDetail.addCell(new Cell(1,9).add(Remarks).setTextAlignment(TextAlignment.LEFT));


            tableShipmentDetail.addCell(new Cell(1,1).add(AccountKey));
            tableShipmentDetail.addCell(new Cell(1,9).add(PickupDate).setTextAlignment(TextAlignment.LEFT));




            document.add(table);
            document.add(table2);
            document.add(tableShipmentDetail);

            document.close();
    }
}