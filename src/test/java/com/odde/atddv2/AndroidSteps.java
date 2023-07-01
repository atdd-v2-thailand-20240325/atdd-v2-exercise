package com.odde.atddv2;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class AndroidSteps {

    @Value("${appium.udid:emulator-5554}")
    private String udid;

    private AndroidDriver<AndroidElement> driver;

    @SneakyThrows
    @When("input username {string}")
    public void inputUsername(String name) {
        AndroidDriver<AndroidElement> driver;
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("udid", udid);
        caps.setCapability("platformName", "Android");
        caps.setCapability("app", "/tmp/app.apk");
        caps.setCapability("uiautomator2ServerInstallTimeout", 120000);
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
        driver.launchApp();

        var userNameInput = driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").text(\"Username\")");
        userNameInput.sendKeys(name);

        TimeUnit.SECONDS.sleep(5);

        driver.quit();
    }

    @After
    public void closeApp() {
        if (driver != null) {
            driver.closeApp();
        }
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String userName, String password) {
        getAndroidDriver().launchApp();
        await().ignoreExceptions().until(() -> getAndroidDriver().findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").text(\"Username\")"), Objects::nonNull).sendKeys(userName);
        await().ignoreExceptions().until(() -> getAndroidDriver().findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\").text(\"Password\")"), Objects::nonNull).sendKeys(password);
        await().ignoreExceptions().until(() -> getAndroidDriver().findElementByAndroidUIAutomator("new UiSelector().text(\"LOGIN\")"), Objects::nonNull).click();
    }

    @Then("{string} should be logged in")
    public void shouldBeLoggedIn(String userName) {
        await().ignoreExceptions().untilAsserted(() -> assertThat(getAndroidDriver().findElementsByAndroidUIAutomator("new UiSelector().text(\"Welcome: " + userName + "\")")).isNotEmpty());
    }

    @Then("login failed error message should be {string}")
    public void loginFailedErrorMessageShouldBe(String message) {
        await().ignoreExceptions().untilAsserted(() -> assertThat(getAndroidDriver().findElementsByAndroidUIAutomator("new UiSelector().text(\"" + message + "\")")).isNotEmpty());
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
