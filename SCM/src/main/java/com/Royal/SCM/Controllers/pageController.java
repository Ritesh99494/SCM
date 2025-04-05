package com.Royal.SCM.Controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pageController {
    @RequestMapping("/Ritesh")
 public String home(Model model) {
       // Add any attributes to the model if needed
       // model.addAttribute("attributeName", attributeValue);
       // Return the name of the view (home.html) to be rendered
       // The view resolver will resolve this to the actual HTML file in the templates directory
       System.out.println("home page");
       model.addAttribute("name", "Ritesh");
       model.addAttribute("age", 25);
              return "home";
 }
    @RequestMapping("/a")
    public String about() {
    
        System.out.println("about page");
        return "about";
}
}
