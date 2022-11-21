package step;

import com.eventstoday.api.entities.Customer;
import com.eventstoday.api.entities.Event;
import com.eventstoday.api.entities.Ticket;
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

public class TicketStepDefinitions {

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

    @When("A ticket request is sent with values {Customer}, {Event}")
    public void aTicketRequestIsSentWithValues(Customer customer, Event event) {
        Ticket ticket = new Ticket(0L, customer , event);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Ticket> request = new HttpEntity<>(ticket, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }

    @Then("A ticket with status {int} is received")
    public void aTicketWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @When("A ticket delete is sent with id value {string}")
    public void aTicketDeleteIsSentWithIdValue(String id_event) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_event);
        testRestTemplate.delete(endpointPath+"/{id}", params);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
    }

    @When("A ticket selected is sent with id value {string}")
    public void aTicketSelectedIsSentWithIdValue(String id_event) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_event);
        Ticket ticket = testRestTemplate.getForObject(endpointPath+"/{id}", Ticket.class, params);
        responseEntity = new ResponseEntity<>(ticket.toString(), HttpStatus.OK);
        System.out.println(ticket.toString());
    }

    @When("All Tickets who are registered in ET")
    public void allTicketsWhoAreRegisteredInET() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        responseEntity = testRestTemplate.exchange(endpointPath, HttpMethod.GET, entity, String.class);
        System.out.println(responseEntity);
    }

    @Then("List of Tickets with status {int} is received")
    public void listOfTicketsWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }
*/
}