package com.odde.atddv2.page;

import com.odde.atddv2.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderPage {

    @Autowired
    public App app;

    public void addOrder(Map<String, String> order) {
        app.clickByText("ADD ORDER");
        order.forEach((placeholder, text) -> {
            if (!placeholder.equals("Status")) {
                app.inputTextByHint(placeholder, text);
            }
        });
        app.selectTextByHint("Please select status", order.get("Status"));
        app.clickByText("SUBMIT");
        app.shouldNotHaveText("SUBMIT");
    }
}
