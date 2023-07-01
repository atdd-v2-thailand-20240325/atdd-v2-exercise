package com.odde.atddv2;

import com.odde.atddv2.page.OrderPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderSteps {

    @Autowired
    private App app;

    @Autowired
    private OrderPage orderPage;

    @When("create order with data below:")
    public void createOrderWithDataBelow(DataTable table) {
        orderPage.addOrder(table.asMaps().get(0));
    }

    @Then("the following order should be displayed:")
    public void theFollowingOrderShouldBeDisplayed(DataTable table) {
        table.asList().forEach(app::shouldHaveText);
    }
}
