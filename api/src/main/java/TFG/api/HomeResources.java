package TFG.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResources {

    @GetMapping("/")
    public String home(){
        return "Welcome to my TFG API";
    }

    @GetMapping("/user")
    public String user(){
        return "This area is only Accessible for Users and Administrators";
    }

    @GetMapping("/admin")
    public String admin()
    {
        return  "this area is only accessible for Administrators";
    }
}
