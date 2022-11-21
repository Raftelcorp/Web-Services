package step;

import com.eventstoday.api.entities.Customer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerStepDefinitions {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endpointPath;
    private ResponseEntity<String> responseEntity;


   @Given("The Endpoint {string} is available")
    public void theEndpointIsAvailable(String endpointPath) {
        this.endpointPath= String.format(endpointPath, randomServerPort);
    }


    @When("A customer request is sent with values {string}, {string}, {int}, {string}")
    public void aCustomerRequestIsSentWithValues(String name, String email, int age, String password) {
        Customer customer = new Customer(0L, name, email, age, password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }

    @Then("A customer with status {int} is received")
    public void aCustomerWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @When("A customer delete is sent with id value {string}")
    public void aCustomerDeleteIsSentWithIdValue(String id_customer) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_customer);
        testRestTemplate.delete(endpointPath+"/{id}", params);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
    }

    @When("A customer selected is sent with id value {string}")
    public void aCustomerSelectedIsSentWithIdValue(String id_customer) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_customer);
        Customer customer = testRestTemplate.getForObject(endpointPath+"/{id}", Customer.class, params);
        responseEntity = new ResponseEntity<>(customer.toString(), HttpStatus.OK);
        System.out.println(customer.toString());
    }

    @When("All Customers who are registered in ET")
    public void allCustomersWhoAreRegisteredInET() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        responseEntity = testRestTemplate.exchange(endpointPath, HttpMethod.GET, entity, String.class);
        System.out.println(responseEntity);
    }

    @Then("List of Customers with status {int} is received")
    public void listOfCustomersWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @When("A customer updated is sent with values {string}, {string}, {string}, {int}, {string}")
    public void aCustomerUpdatedIsSentWithValues(String id_customer, String name, String email, int age, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id_customer);
        Customer customerUpdated = new Customer(0L, name,email,age,password);
        testRestTemplate.put(endpointPath+"/{id}", customerUpdated, params);
        responseEntity = new ResponseEntity<>(customerUpdated.toString(), HttpStatus.OK );
    }
}