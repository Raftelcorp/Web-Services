Feature: Ticket Testing

  Background:
    Given The Endpoint "http://localhost:%d/api/tickets" is available

  @post-ticket
  Scenario: Add Ticket
    When A ticket request is sent with values 1, 2
    Then A ticket with status 201 is received

  @delete-ticket
  Scenario: Delete Ticket
    When A ticket delete is sent with id value "1"
    Then A ticket with status 200 is received

  @get-ticket-by-id
  Scenario: Get Ticket By Id
    When A ticket selected is sent with id value "2"
    Then A ticket with status 200 is received

  @get-all-tickets
  Scenario: Get All Tickets
    When All Tickets who are registered in ET
    Then List of Tickets with status 200 is received