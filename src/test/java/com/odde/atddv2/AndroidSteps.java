package com.odde.atddv2;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.en.Given;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidSteps {
    @SneakyThrows
    @Given("input username {string}")
    public void inputUserName(String name) {
        AndroidDriver<AndroidElement> driver;
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("udid", "emulator-5558");
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
