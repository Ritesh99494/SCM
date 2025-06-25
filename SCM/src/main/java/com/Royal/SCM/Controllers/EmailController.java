package com.Royal.SCM.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.SendEmailRequest;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    
    @Autowired
    private JavaMailSender mailSender;

  @Value("${spring.mail.username}")
   private String defaultFromEmail;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody SendEmailRequest request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getTo());
            message.setSubject(request.getSubject());
            message.setText(request.getBody());
            message.setFrom(defaultFromEmail);

            mailSender.send(message);
            return ResponseEntity.ok("✅ Email sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("❌ Failed to send email: " + e.getMessage());
        }
    }

}
