package com.joselv.mobile.hooks;

import com.joselv.mobile.core.DriverFactory;
import com.joselv.mobile.core.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Cucumber lifecycle hooks: one fresh driver session per scenario,
 * with a screenshot attached to the report whenever a scenario fails.
 */
public class Hooks {

    @Before
    public void startSession(Scenario scenario) {
        DriverManager.setDriver(DriverFactory.createDriver());
    }

    @After
    public void endSession(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        } catch (Exception ignored) {
            // never let teardown mask the real test failure
        } finally {
            DriverManager.quitDriver();
        }
    }
}
