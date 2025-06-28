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
import com.Royal.SCM.helpers.Message;
import com.Royal.SCM.helpers.MessageType;
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
@GetMapping("/about")
public String about() {
    System.out.println("about page");
    return "about";
}

  // services

  @RequestMapping("/services")
  public String servicesPage() {
      System.out.println("services page loading");
      return "services";
  }

  // contact page

  @GetMapping("/contact")
  public String contact() {
      return new String("contact");
  }

  // this is showing login page
  @GetMapping("/login")
  public String login() {
      return new String("login");
  }
  // registration page
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
        if (rBindingResult.hasErrors()) {
            return "register";
        }

        // save to database

        // userservice

        // UserForm--> User
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic(
                "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75");

        User savedUser = userService.saveUser(user);
        System.out.println("User saved: " + savedUser);
        // message = "Registration Successful"

        // add the message:

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);

        // redirectto login page
        return "redirect:/register";
    }


}
