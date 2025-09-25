package uk.gov.hmcts.cp.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.cucumber.core.options.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameters(value = {
    @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "uk.gov.hmcts.cp.bdd"),
    @ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:build/reports/cucumber/cucumber-report.html"),
    @ConfigurationParameter(
        key = FILTER_TAGS_PROPERTY_NAME,
        value = "not @Ignore")
})
@CucumberContextConfiguration
@SpringBootTest
@ActiveProfiles("integration")
public class CucumberRunner {
}
