package com.odde.atddv2;

import com.github.leeonky.cucumber.restful.RestfulStep;
import io.cucumber.java.en.Then;
import lombok.SneakyThrows;

public class RelationsStep {

    @SneakyThrows
    @Then("API {string} should be:")
    public void apiShouldBe(String url, String expression) {
        RestfulStep restfulStep = new RestfulStep();
        restfulStep.setBaseUrl("http://localhost:8080");
        restfulStep.getAndResponseShouldBe(url, expression);
    }
}
