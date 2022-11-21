Feature: Event Testing

  Background:
    Given The Endpoint "http://localhost:%d/api/events" is available

  @post-event
  Scenario: Add Event
    When A event request is sent with values "Evento Final", "Lucas", "evento de fin de año", "120", "url.png"
    Then A event with status 201 is received

  @delete-event
  Scenario: Delete Event
    When A event delete is sent with id value "1"
    Then A event with status 200 is received

  @get-event-by-id
  Scenario: Get Event By Id
    When A event selected is sent with id value "1"
    Then A event with status 200 is received

  @get-all-events
  Scenario: Get All Events
    When All Events who are registered in ET
    Then List of Events with status 200 is received