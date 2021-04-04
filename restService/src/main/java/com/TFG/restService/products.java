package com.TFG.restService;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import platform.*;

import java.util.List;

@RestController
public class products {

    @RequestMapping(method = RequestMethod.GET, path = "/allProducts")
    public List<product> getProducts()
    {
        return RestServiceApplication.bddProductos;
    }




}
