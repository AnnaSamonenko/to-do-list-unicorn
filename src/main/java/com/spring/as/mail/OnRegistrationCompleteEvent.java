package com.spring.as.mail;

import com.spring.as.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    @Getter
    @Setter
    private String appUrl;

    @Getter
    @Setter
    private Locale locale;

    @Getter
    @Setter
    private User user;

    public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
