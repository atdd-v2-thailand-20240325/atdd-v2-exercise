package com.odde.atddv2;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.en.Given;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidSteps {

    @Value("${appium.udid:emulator-5558}")
    private String udid;

    @SneakyThrows
    @Given("input username {string}")
    public void inputUserName(String name) {
        AndroidDriver<AndroidElement> driver;
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("udid", udid);
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("platformName", "Android");
        caps.setCapability("app", "/tmp/app.apk");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
        driver.launchApp();

        TimeUnit.SECONDS.sleep(10);

        // Input username in edit

        driver.quit();
    }

}
