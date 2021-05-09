package net.codejava;

import net.codejava.Box.BoxService;
import net.codejava.Products.ProductService;
import net.codejava.Purchase.Purchase;
import net.codejava.Purchase.PurchaseService;
import net.codejava.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/box")
public class boxController {

    @Autowired
    private ProductService service;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private BoxService boxService;


    @RequestMapping(value = "/deliveries", method = RequestMethod.GET)
    @ResponseBody
    public List<Purchase> getBoxDeliveries(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Purchase> allPurchases = purchaseService.listAll();
        List<Purchase>myPurchaseList = new ArrayList<Purchase>() ;
        long myID = ((MyUserDetails)principal).getId();
        System.out.println(myID);
        for (int i =0; i< allPurchases.size() ;i++)
        {
            if(allPurchases.get(i).getBox_id() == myID)
            {
                System.out.println(allPurchases.get(i));
                myPurchaseList.add(allPurchases.get(i));
            }
        }
        System.out.println(myPurchaseList.toString());
        return myPurchaseList;
    }

    @RequestMapping(value = "/allDeliveries", method = RequestMethod.GET)
    @ResponseBody
    public List<Purchase> allDeliveries(){
        List<Purchase> allPurchases = purchaseService.listAll();

        return allPurchases;
    }
}
