package com.tddacademy.zoo.service;

public class NotificationService {
    
    public void sendEmail(String to, String subject, String message) {
        // This would normally send an email
        System.out.println("Email sent to: " + to + " - Subject: " + subject + " - Message: " + message);
    }
    
    public void sendSMS(String phoneNumber, String message) {
        // This would normally send an SMS
        System.out.println("SMS sent to: " + phoneNumber + " - Message: " + message);
    }
    
    public boolean isEmailServiceAvailable() {
        // This would normally check if email service is available
        return true;
    }
    
    public int getNotificationCount() {
        // This would normally return the count of sent notifications
        return 0;
    }
} 