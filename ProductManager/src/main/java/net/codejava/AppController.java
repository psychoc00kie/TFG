package net.codejava;

import java.util.List;

import net.codejava.Products.Product;
import net.codejava.Products.ProductService;
import net.codejava.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private ProductService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "index";
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
	
	@RequestMapping("/new")
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
