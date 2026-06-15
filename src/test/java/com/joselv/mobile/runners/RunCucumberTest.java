package com.joselv.mobile.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * Entry point: runs every {@code .feature} under {@code src/test/resources/features}
 * through the JUnit Platform. Filter with {@code -Dcucumber.filter.tags="@smoke"}.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.joselv.mobile.steps,com.joselv.mobile.hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber-report/cucumber.html, json:target/cucumber-report/cucumber.json")
public class RunCucumberTest {
}
