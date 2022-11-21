Feature: Customer Testing

  Background:
    Given The Endpoint "http://localhost:%d/api/customers" is available

  @post-customer
  Scenario: Add Customer
    When A customer request is sent with values "Diego", "diego@gmail.com", 22, "1234"
    Then A customer with status 201 is received

  @delete-customer
  Scenario: Delete Customer
    When A customer delete is sent with id value "1"
    Then A customer with status 200 is received

  @get-customer-by-id
  Scenario: Get Customer By Id
    When A customer selected is sent with id value "1"
    Then A customer with status 200 is received

  @get-all-customers
  Scenario: Get All Customers
    When All Customers who are registered in ET
    Then List of Customers with status 200 is received

  @update-customer-by-id
  Scenario: Update Customer By Id
    When A customer updated is sent with values "1", "Diego", "diego.martinez@gmail.com", 23, "321"
    Then A customer with status 200 is received