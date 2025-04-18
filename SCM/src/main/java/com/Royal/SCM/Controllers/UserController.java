package com.Royal.SCM.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

      // user dashbaord page

      @RequestMapping(value = "/dashboard")
      public String userDashboard() {
          System.out.println("User dashboard");
          return "user/dashboard";
      }
       // user profile page

    @RequestMapping(value = "/profile")
    public String userProfile() {

        return "user/profile";
    }
}
