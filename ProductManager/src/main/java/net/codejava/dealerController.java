package net.codejava;


import net.codejava.Box.BoxService;
import net.codejava.Products.Product;
import net.codejava.Products.ProductService;
import net.codejava.Purchase.Purchase;
import net.codejava.Purchase.PurchaseService;
import net.codejava.QR.qrGenerator;
import net.codejava.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping(value = "/dealer")
public class dealerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private BoxService boxService;

    @RequestMapping(value = "/qr", method = RequestMethod.GET)
    public void getQR(HttpServletResponse response) throws Exception{

    response.setContentType("image/png");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String myID = ((MyUserDetails)principal).getId().toString();

        OutputStream outputStream = response.getOutputStream();
        outputStream.write(qrGenerator.generateQR(myID,500,500));
        outputStream.flush();
        outputStream.close();

    }

    @RequestMapping(value = "/getDeliveries", method = RequestMethod.GET)
    public String deliveries(Model model){
        List<Purchase> deliveries = purchaseService.listAll();

        model.addAttribute("deliveries", deliveries);
        System.out.println(deliveries.toString());
        return "dealer";
    }



}
