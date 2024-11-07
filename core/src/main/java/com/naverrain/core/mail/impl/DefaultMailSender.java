package com.naverrain.core.mail.impl;

import com.naverrain.core.mail.MailSender;

public class DefaultMailSender implements MailSender {

    private static DefaultMailSender instance;
    private DefaultMailSender() {};

    public static DefaultMailSender getInstance(){
        if (instance == null){
            instance = new DefaultMailSender();
        }
        return instance;
    }

    @Override
    public void sendEmail(String SendTo, String sendText) {

    }
}
