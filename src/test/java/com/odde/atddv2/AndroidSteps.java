package com.odde.atddv2;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.zh_cn.假如;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidSteps {

    @Value("${appium.udid:emulator-5558}")
    private String udid;
    private AndroidDriver<AndroidElement> driver;

    @SneakyThrows
    @Given("input username {string}")
    public void inputUsername(String name) {
        AndroidDriver<AndroidElement> driver = getAndroidDriver();
        driver.launchApp();

        var userNameInput = driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").text(\"Username\")");
        userNameInput.sendKeys(name);

        TimeUnit.SECONDS.sleep(5);

        driver.quit();
    }

    private AndroidDriver<AndroidElement> getAndroidDriver() {
        if (driver == null)
            driver = createWebDriver();
        return driver;
    }

    @SneakyThrows
    private AndroidDriver<AndroidElement> createWebDriver() {
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("udid", udid);
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("platformName", "Android");
        caps.setCapability("app", "/tmp/app.apk");
        return new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

}
