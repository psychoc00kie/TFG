package net.codejava;

import net.codejava.Repositories.Box.BoxService;
import net.codejava.Repositories.Products.ProductService;
import net.codejava.Repositories.Purchase.Purchase;
import net.codejava.Repositories.Purchase.PurchaseService;
import net.codejava.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/box")
public class BOX {

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private BoxService boxService;

    @GetMapping("/deliveries")
    public List<Purchase> getBoxDeliveries(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Purchase> myPurchaseList = purchaseService.listAll();
        long myID = ((MyUserDetails)principal).getId();
        for (int i =0; i< myPurchaseList.size() ;i++)
        {
            if(myPurchaseList.get(i).getBuyer_id() != myID)
            {
                myPurchaseList.remove(i);
            }
        }
        return myPurchaseList;
    }


}
