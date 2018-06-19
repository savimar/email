package com.bellintergator.email.service;

public interface EmailService  {
   boolean sendMail(String to, String subject, String text);

}
