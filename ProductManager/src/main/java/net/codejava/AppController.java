package net.codejava;

import java.util.List;

import net.codejava.Box.Box;
import net.codejava.Box.BoxService;
import net.codejava.Products.Product;
import net.codejava.Products.ProductService;
import net.codejava.Purchase.Purchase;
import net.codejava.Purchase.PurchaseService;
import net.codejava.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Null;

@Controller
public class AppController {
	@Autowired
	private ProductService service;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private BoxService boxService;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "index";
	}

	@RequestMapping(value = "/getBoxDeliveries", method = RequestMethod.GET)
	@ResponseBody
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

	@RequestMapping(value = "/bot", method = RequestMethod.GET)
	@ResponseBody
	public String showBotArea(){
		System.out.println("test");
		String username = "null";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof MyUserDetails) {
			 username = ((MyUserDetails)principal).getId().toString();
		} else {
			 username = principal.toString();
		}

		return username;
	}
	
	@RequestMapping("/newProduct")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}


	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/";
	}

	@RequestMapping(value = "/savePurchase",method = RequestMethod.POST)
	public String savePurchase(@ModelAttribute("purchase") Purchase purchase){

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		System.out.println(purchase.toString());
		purchase.setIdpurchase((long) (purchaseService.listAll().size()+1));
		System.out.println(purchase.toString());
		purchase.setBuyer_id((long)((MyUserDetails)principal).getId());
		System.out.println(purchase.toString());
		purchase.setCourier_id((long)5);
		System.out.println(purchase.toString());

		for (Box aux : boxService.listAll())
		{
			if(aux.getId_user() == purchase.getBox_id())
				purchase.setDelivery_address(aux.getAddress());
		}
		System.out.println(purchase.toString());

		purchaseService.save(purchase);

		return "redirect:/";
	}

	@RequestMapping("/newPurchase")
	public String showNewPurchaseForm(Model model) {


		return "new_purchase";
	}

	@RequestMapping("/purchase/{id}")
	public ModelAndView showPurchaseForm(@PathVariable(name = "id") Long id){

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Product product = service.get(id);
		Purchase purchase = new Purchase((Long) null,((MyUserDetails)principal).getId(),(Long)product.getId(),0,product.getPrice(), (Long) null, (Long) null,(String) null);
		List<Box> boxList = boxService.listAll();
		for(Box aux : boxList)
		{
			System.out.println(aux.toString());
		}
		for(int i = 0; i<boxList.size();i++){
			if(boxList.get(i).getId_owner() != ((MyUserDetails)principal).getId())
				boxList.remove(i);
		}


		ModelAndView mav = new ModelAndView("new_purchase");
		mav.addObject("product", product);
		mav.addObject("purchase",purchase);
		mav.addObject("boxList",boxList);
		return mav;
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_product");
		
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
}
