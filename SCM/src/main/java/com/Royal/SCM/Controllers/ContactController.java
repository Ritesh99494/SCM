package com.Royal.SCM.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

import org.slf4j.Logger;

import com.Royal.SCM.entities.Contact;
import com.Royal.SCM.entities.User;
import com.Royal.SCM.forms.ContactForm;
import com.Royal.SCM.forms.ContactSearchForm;
import com.Royal.SCM.helpers.APPConstants;
import com.Royal.SCM.helpers.Helper;
import com.Royal.SCM.helpers.Message;
import com.Royal.SCM.helpers.MessageType;
import com.Royal.SCM.servises.ContactService;
import com.Royal.SCM.servises.ImageService;
import com.Royal.SCM.servises.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

     @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
   @Autowired
    private ImageService imageService;

    @RequestMapping("/add")
    // add contact page: handler
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();

        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }
     @RequestMapping(value = "/add", method = RequestMethod.POST)
     public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,
            Authentication authentication, HttpSession session) {

        // process the form data

        // 1 validate form

        if (result.hasErrors()) {

            result.getAllErrors().forEach(error -> logger.info(error.toString()));

            session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build());
            return "user/add_contact";
        }
        String username = Helper.getEmailOfLoggedInUser(authentication);
        // form ---> contact

        User user = userService.getUserByEmail(username);

                // uplod karne ka code
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());

        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            String filename = UUID.randomUUID().toString();
           String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);
            contact.setPicture(fileURL);
            contact.setCloudinaryImagePublicId(filename);

        }
        contactService.save(contact);
        System.out.println(contactForm);

        // 3 set the contact picture url

        // 4 `set message to be displayed on the view

        session.setAttribute("message",
                Message.builder()
                        .content("You have successfully added a new contact")
                        .type(MessageType.green)
                        .build());

        return "redirect:/user/contacts/add";
}
//view contacts
 public String viewContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = APPConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model,
            Authentication authentication) {

                // load all the user contacts
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getByUser(user, page, size, sortBy, direction);

        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", APPConstants.PAGE_SIZE);

        model.addAttribute("contactSearchForm", new ContactSearchForm());

        return "user/contacts";
               
            }


}

