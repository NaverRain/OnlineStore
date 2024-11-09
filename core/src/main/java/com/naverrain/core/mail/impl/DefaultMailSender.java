package com.naverrain.core.mail.impl;

import com.naverrain.core.mail.MailSender;
import org.springframework.stereotype.Service;

@Service
public class DefaultMailSender implements MailSender {

    @Override
    public void sendEmail(String SendTo, String sendText) {

    }
}
