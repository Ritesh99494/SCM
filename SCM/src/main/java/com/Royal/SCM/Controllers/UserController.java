package com.Royal.SCM.Controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

      // user dashbaord page

      @RequestMapping(value = "/dashboard")
      public String userDashboard() {
          System.out.println("User dashboard");
          return "user/dashboard";
      }
       // user profile page

    @RequestMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {

        return "user/profile";
    }


}
