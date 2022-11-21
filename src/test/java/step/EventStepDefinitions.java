package step;

import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.entities.Event;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EventStepDefinitions {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endpointPath;
    private ResponseEntity<String> responseEntity;


 /*  @Given("The Endpoint {string} is available")
    public void theEndpointIsAvailable(String endpointPath) {
        this.endpointPath= String.format(endpointPath, randomServerPort);
    }

    @When("A event request is sent with values {string}, {string}, {string}, {string}, {string}")
    public void aEventRequestIsSentWithValues(String title, String author, String description, String price, String url) {
        Date start = new Date(2022, 3, 2);
        Date end = new Date(2022, 5, 2);
       Customer customer1 = new Customer();
        customer1.setId(1L);
        Event event = new Event(0L, title  , author , description,price, start, end , url , customer1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Event> request = new HttpEntity<>(event, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }

    @Then("A event with status {int} is received")
    public void aEventWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @When("A event delete is sent with id value {string}")
    public void aEventDeleteIsSentWithIdValue(String id_event) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_event);
        testRestTemplate.delete(endpointPath+"/{id}", params);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
    }

    @When("A event selected is sent with id value {string}")
    public void aEventSelectedIsSentWithIdValue(String id_event) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_event);
        Customer customer = testRestTemplate.getForObject(endpointPath+"/{id}", Customer.class, params);
        responseEntity = new ResponseEntity<>(customer.toString(), HttpStatus.OK);
        System.out.println(customer.toString());
    }

    @When("All Events who are registered in ET")
    public void allEventsWhoAreRegisteredInET() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        responseEntity = testRestTemplate.exchange(endpointPath, HttpMethod.GET, entity, String.class);
        System.out.println(responseEntity);
    }

    @Then("List of Events with status {int} is received")
    public void listOfEventsWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }
    */

}