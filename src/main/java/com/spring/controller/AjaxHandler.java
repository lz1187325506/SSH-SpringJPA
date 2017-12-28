package com.spring.controller;

import com.hibernate.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;
/**
*@see AjaxHandler ajax处理controller
*@author eric
*@version 
*/
@Controller
public class AjaxHandler  {
    public AjaxHandler () {}
    @PostMapping("/ajax")
    @ResponseBody
    public  User jsonHandler(@RequestBody User user){
        System.out.println(user.getUsername());
        User user2 = new User("name","password");
        return user2;
    }

}

@ControllerAdvice
class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public JsonpAdvice() {
        super("callback");
    }
}