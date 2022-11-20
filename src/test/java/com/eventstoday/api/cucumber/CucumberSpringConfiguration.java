package com.eventstoday.api.cucumber;


import com.eventstoday.api.EventstodayApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = EventstodayApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = EventstodayApplication.class,
        loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
