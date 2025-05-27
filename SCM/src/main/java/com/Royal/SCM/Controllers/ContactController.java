package com.Royal.SCM.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    public String addContactView() {
       
        return "user/add_contact";
    } 
        
    
}

