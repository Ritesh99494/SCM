package com.Royal.SCM.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;

import com.Royal.SCM.forms.ContactForm;
import com.Royal.SCM.helpers.Message;
import com.Royal.SCM.helpers.MessageType;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);



    @RequestMapping("/add")
    // add contact page: handler
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();

        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }
    // @RequestMapping(value = "/add", method = RequestMethod.POST)
    //  public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,
    //         Authentication authentication, HttpSession session) {

    //     // process the form data

    //     // 1 validate form

    //     if (result.hasErrors()) {

    //         result.getAllErrors().forEach(error -> logger.info(error.toString()));

    //         session.setAttribute("message", Message.builder()
    //                 .content("Please correct the following errors")
    //                 .type(MessageType.red)
    //                 .build());
    //         return "user/add_contact";
    //     }
        
    // }
}

