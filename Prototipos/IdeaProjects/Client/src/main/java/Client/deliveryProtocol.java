package Client;

import net.glxn.qrgen.javase.QRCode;
import org.apache.commons.codec.digest.DigestUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;

public class deliveryProtocol {
    public static void main(String[] args) throws IOException {

        boolean box_error_check;
        boolean delivery_error_check;
        boolean ready_for_exchange;

        /*
        WE OPEN A NEW CONNECTION FOR BOTH PARTS
         */

        connection box = new connection(InetAddress.getByName("127.0.0.1"),1); // type 1 for box
        connection delivery = new connection(InetAddress.getByName("127.0.0.1"),0); // type 0 delivery
        /*
        WE CHECK BOTH CONNECTIONS BEFORE INITIALIZING THE PROTOCOL
         */
        box_error_check = box.checkConnectionStatus();
        delivery_error_check = delivery.checkConnectionStatus();

        if (!box_error_check)
            System.out.println("check connection logs, something went wrong, with the Box");
        else if(!delivery_error_check)
            System.out.println("check connection logs, something went wrong, with the Delivery");

        /*
        WE GENERATE THE TOKENS FOR BOT ASSETS
         */
        //Send to the box the token for the QR   This part is not on use right now.
        String token_test = "5616";  //we send the hased value of the box id
        String box_token = generateToken(token_test);

        token_test = "6516818";  //we send the hased value of the box id
        String delivery_token = generateToken(token_test);

        System.out.println(box_token+" length: "+box_token.length());
        System.out.println(delivery_token);

        /*
        WE SEND TOKENS TO BOTH PARTS SO THEY CAN AUTHENTICATE
         */
        box_error_check=box.sendToken(box_token);
        delivery_error_check=delivery.sendToken(delivery_token);




        /*
        WE CHECK IF BOTH ARE READY FOR EXCHANGE
         */

        // Box is automatically ready if the token is successfully sent, as it does not have to move.
        // Courier, however, has to travel to the box destination. for testing purposes, we will delay the arrival only 10s, but this should take longer.

        int et = delivery.courierDepart(generateToken("35165"));
        System.out.println(et);
        box.startAuthentication();







        box.closeConnection();
        delivery.closeConnection();

    }

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {  //This method is to generate a QR code in the future for the display
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

        return ImageIO.read(bis);
    }
/*
    public static connection synchronize_connection(Client client, connection con, int type) {
*//*        if the type is 1 its a box connection and if its 0 its a delivery
        open a connection with the Box an verify connection is ok*//*
        if (type == 1)
            con = client.OpenConnection(5056); // For box conections port will be 5056
        else if (type == 0)
            con = client.OpenConnection(9999); //For delivery connection we will use port 9999

        return con;   // we return the connection updated for the type.
        //Server Checks connection and establishes a socket.


    }*/

    public static boolean errorCheck(@org.jetbrains.annotations.NotNull connection con) {
        if (con.getE() != null) {
            String msg = con.getE().getMessage();
            if (msg.equalsIgnoreCase("Connection refused: connect")) {
                System.out.println("conection fail true");
                return false;
            } else {
                con.getE().printStackTrace();
                return false;
            }
        }  //Connection not OK.

        System.out.println("Connection successful");
        return true;

    }

    public static String generateToken(String input){
        //String token = "the location of the box and also the id of the user";
        String token = new DigestUtils("SHA3-256").digestAsHex(input);
        return token;
    }

    public static boolean sendTokens(connection box, connection delivery){
        return true;
    }

}
