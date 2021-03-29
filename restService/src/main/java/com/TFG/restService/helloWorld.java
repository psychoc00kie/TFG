package com.TFG.restService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class helloWorld {

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String helloWorld(){
        return "Hi Everyone";
    }

    @RequestMapping(method = RequestMethod.GET,path = "/hi")
    public String hiUser(@PathParam("name") String _nme)
    {
        return "Hi: "+_nme;
    }
}
