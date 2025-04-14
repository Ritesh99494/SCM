package com.Royal.SCM.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import com.Royal.SCM.entities.User;
import com.Royal.SCM.forms.UserForm;
import com.Royal.SCM.servises.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class pageController {
    @Autowired
    private UserService userService;
    @RequestMapping("/home")
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
@GetMapping("/register")
public String register(Model model) {
    UserForm  userForm= new UserForm();
    model.addAttribute( "userForm",userForm);
    return "register";
}
//processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
            HttpSession session) {
        System.out.println("Processing registration");
        // fetch form data
        // UserForm
        System.out.println(userForm);

        // validate form data
      

        // TODO::Validate userForm[Next Video]

        // save to database

        // userservice

        // UserForm--> User
        User user = User.builder()
         .name(userForm.getName())
         .email(userForm.getEmail())
         .password(userForm.getPassword())
         .about(userForm.getAbout())
        .phoneNumber(userForm.getPhoneNumber())
         .profilePic(
         "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75")
         .build();
           User savedUser = userService.saveUser(user);
        System.out.println("User saved: " + savedUser);

        // redirectto login page
        return "redirect:/register";
    }


}
