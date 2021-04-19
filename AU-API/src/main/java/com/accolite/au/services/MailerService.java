package com.accolite.au.services;

import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

public interface MailerService {
    @Async
    void SendMail(int sessionId) throws IOException;

}
