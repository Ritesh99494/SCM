package com.Royal.SCM.servises;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
    void sendEmailWithHtml();
    void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath);

}
