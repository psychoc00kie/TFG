package net.codejava.QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageWriter;
import java.io.ByteArrayOutputStream;

public class qrGenerator {

    public static byte[] generateQR(String text , int width, int height){
        try {

            QRCodeWriter barcodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = barcodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,"png",byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
