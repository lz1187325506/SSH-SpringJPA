package com.spring.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hibernate.model.User;
import com.spring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
*@see DefaultController 默认控制器
*@author eric
*@version 
*/
@Controller
public class DefaultController {
    public DefaultController(){};

    @Resource
    private UserService userService;
    
    @GetMapping("/")
    public void getDefaultPage(HttpServletRequest request,HttpServletResponse response){
        try {
			request.getRequestDispatcher("/index.html").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @GetMapping("/hello")
    public String getHelloJsp(HttpServletRequest request){
        request.setAttribute("username", "sss");
        return "hello";
    }

    @PostMapping("/regest")
    @ResponseBody
    public User regestUser(String username,String password){
        User user = new User(username, password);
        return userService.saveUser(user);
        
    }


    @GetMapping("/load")
    public ModelAndView getLazyLoad(){
       User user = userService.findById(7);
        ModelAndView mAndView = new ModelAndView("hello");
        mAndView.addObject("user", user);
        System.out.println("servlet over");
        return mAndView;
    }
}