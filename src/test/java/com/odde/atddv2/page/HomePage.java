package com.odde.atddv2.page;

import com.odde.atddv2.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomePage {

    @Autowired
    public App app;

    public void open() {
        app.launch();
    }

    public void login(String userName, String password) {
        app.inputTextByHint("Username", userName);
        app.inputTextByHint("Password", password);
        app.clickByText("LOGIN");
    }
}
